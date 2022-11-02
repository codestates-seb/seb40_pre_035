package stackoverflow.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import stackoverflow.domain.File.service.FileService;
import stackoverflow.domain.account.dto.PatchAccountReqDto;
import stackoverflow.domain.account.dto.PostAccountReqDto;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.domain.answer.repository.AnswerRepository;
import stackoverflow.domain.question.repository.QuestionRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

import java.io.IOException;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

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
    public Account modifyAccount(PatchAccountReqDto modifyAccountReqDto, Long loginAccountId) {
        Account account = findAccount(modifyAccountReqDto.getAccountId());
        verifyAuthority(account, loginAccountId);

        Optional.ofNullable(modifyAccountReqDto.getPassword())
                .ifPresent(password -> account.setPassword(password));
        Optional.ofNullable(modifyAccountReqDto.getNickname())
                .ifPresent(nickName -> account.setNickname(nickName));

        //파일 저장 후 파일 경로를 account 에 저장
        Optional<MultipartFile> optionalProfile = Optional.ofNullable(modifyAccountReqDto.getProfile());
        if (optionalProfile.isPresent()) {
            String filePath = null;
            try {
                filePath = fileService.storeFile(optionalProfile.get(), path);
            } catch (IOException e) {
                throw new RuntimeException("프로필 저장에 실패했습니다.");
            }
            account.setProfile(filePath);
        }

        return accountRepository.save(account);
    }

    @Transactional
    public void removeAccount(Account findAccount, Long loginAccountId) {
        verifyAuthority(findAccount, loginAccountId);
//        answerRepository.deleteAllById();
        accountRepository.delete(findAccount);
    }

    public Account findAccount(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        return optionalAccount.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));
    }

    private void verifyAuthority(Account findAccount, Long loginAccountId) {
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
}
