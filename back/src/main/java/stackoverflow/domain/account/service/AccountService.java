package stackoverflow.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stackoverflow.domain.File.service.FileService;
import stackoverflow.domain.account.dto.PatchAccountReqDto;
import stackoverflow.domain.account.dto.PostAccountReqDto;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.repository.AccountRepository;
import stackoverflow.global.exception.advice.BusinessLogicException;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final FileService fileService;

    @Value("${file.img}")
    private String path;

    public Account addAccount(PostAccountReqDto postAccountReqDto) {
        verifyAccountExist(postAccountReqDto.getEmail());
        Account account = setDefaultProperties(postAccountReqDto);

        return accountRepository.save(account);
    }

    public Account modifyAccount(PatchAccountReqDto modifyAccountReqDto) {
        Account account = findAccount(modifyAccountReqDto.getAccountId());

        Optional.ofNullable(modifyAccountReqDto.getPassword())
                .ifPresent(password -> account.setPassword(password));
        Optional.ofNullable(modifyAccountReqDto.getNickname())
                .ifPresent(nickName -> account.setNickname(nickName));

        //파일 저장 후 파일 경로를 account 에 저장
        //Todo 현재 문제점: 수정하면서 이전의 파일을 지우지 않음
        Optional<MultipartFile> optionalProfile = Optional.ofNullable(modifyAccountReqDto.getProfile());
        if (optionalProfile.isPresent()) {
            String filePath = null;
            try {
                filePath = fileService.storeFile(optionalProfile.get(), path);
            } catch (IOException e) {
                throw new RuntimeException("프로필 저장에 실패했습니다.");
            }
            int start = filePath.lastIndexOf("/");
            String fileName = filePath.substring(start);
            String profilePath = "/file" + fileName;
            account.setProfile(profilePath);
        }

        return accountRepository.save(account);
    }

    public Account findAccount(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        return optionalAccount.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_ACCOUNT));
    }

    private void verifyAccountExist(String email) {
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
        int start = filePath.lastIndexOf("/");
        String fileName = filePath.substring(start);
        String profilePath = "/file" + fileName;

        Account createAccount = postAccountReqDto.toAccount(profilePath);
        createAccount.setRole("USER");

        return createAccount;
    }
}
