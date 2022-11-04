package stackoverflow.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import stackoverflow.domain.File.service.FileService;
import stackoverflow.domain.account.dto.PatchAccountReqDto;
import stackoverflow.domain.account.dto.PostAccountReqDto;
import stackoverflow.domain.account.dto.ProfileAccountResDto;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.entity.AnswerVote;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.domain.question.entity.QuestionVote;
import stackoverflow.global.common.enums.VoteState;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final FileService fileService;

    @Value("${file.img}")
    private String path;

    @Transactional
    public Account addAccount(PostAccountReqDto postAccountReqDto) {
        verifyEmailExist(postAccountReqDto.getEmail());
        Account account = setDefaultProperties(postAccountReqDto);

        return accountRepository.save(account);
    }

    @Transactional
    public Account modifyAccount(Account findAccount, PatchAccountReqDto modifyAccountReqDto) {

        Optional.ofNullable(modifyAccountReqDto.getPassword())
                .ifPresent(password -> {
                    findAccount.setPassword(password);
                    findAccount.setModifiedAt(LocalDateTime.now());
                });
        Optional.ofNullable(modifyAccountReqDto.getNickname())
                .ifPresent(nickName -> {
                    findAccount.setNickname(nickName);
                    findAccount.setModifiedAt(LocalDateTime.now());
                });

        //파일 저장 후 파일 경로를 findAccount 에 저장
        Optional<MultipartFile> optionalProfile = Optional.ofNullable(modifyAccountReqDto.getProfile());
        if (optionalProfile.isPresent() && !optionalProfile.get().isEmpty()) {
            String filePath = null;
            try {
                filePath = fileService.storeFile(optionalProfile.get(), path);
            } catch (IOException e) {
                throw new RuntimeException("프로필 저장에 실패했습니다.");
            }
            findAccount.setProfile(filePath);
            findAccount.setModifiedAt(LocalDateTime.now());
        }



        return accountRepository.save(findAccount);
    }

    @Transactional
    public void removeAccount(Account findAccount) {

        accountRepository.delete(findAccount);
    }


    public Account findAccount(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        return optionalAccount.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));
    }

    public void verifyAuthority(Account findAccount, Long loginAccountId) {
        if (!findAccount.getId().equals(loginAccountId)) {
            throw new BusinessLogicException(ExceptionCode.NON_ACCESS_MODIFY);
        }
    }

    private void verifyEmailExist(String email) {
        Optional<Account> optionalAccount = accountRepository.findByEmail(email);

        if (optionalAccount.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.ACCOUNT_EXIST);
        }
    }

    private Account setDefaultProperties(PostAccountReqDto postAccountReqDto) {  //이후에 구체화 예정
        MultipartFile profile = postAccountReqDto.getProfile();

        if (profile.isEmpty()) {
            throw new BusinessLogicException(ExceptionCode.NOT_PROFILE_IMG);
        }

        String filePath = null;
        try {
            filePath = fileService.storeFile(profile, path);
        } catch (IOException e) {
            throw new RuntimeException("프로필 저장에 실패했습니다.");
        }
        Account createAccount = postAccountReqDto.toAccount(filePath);
        createAccount.setRole("USER");

        return createAccount;
    }

    public ProfileAccountResDto findProfileAccount(long accountId) {
        Account account = findAccount(accountId);
        List<Question> questionsList = account.getQuestionsList();
        List<Answer> answerList = account.getAnswerList();

        long totalQuestions = questionsList.size();
        long totalAnswers = answerList.size();
        long totalVotes = 0;


        for (int i = 0; i < totalQuestions; i++) {
            List<QuestionVote> questionVotes = questionsList.get(i)
                    .getQuestionVotes();

            long questionUPVote = questionVotes.stream()
                    .map(QuestionVote::getState)
                    .filter(voteState -> voteState.equals(VoteState.UP))
                    .count();

            totalVotes += 2 * questionUPVote - questionVotes.size();
        }

        for (int i = 0; i < totalAnswers; i++) {
            List<AnswerVote> answerVotes = answerList.get(i)
                    .getAnswerVotes();

            long answerUPVote = answerVotes.stream()
                    .map(AnswerVote::getState)
                    .filter(voteState -> voteState.equals(VoteState.UP))
                    .count();

            totalVotes += 2 * answerUPVote - answerVotes.size();

        }

        return ProfileAccountResDto.builder()
                .email(account.getEmail())
                .nickname(account.getNickname())
                .profile(account.getProfile())
                .totalVotes(totalVotes)
                .totalAnswers(totalAnswers)
                .totalQuestions(totalQuestions)
                .build();
    }
}
