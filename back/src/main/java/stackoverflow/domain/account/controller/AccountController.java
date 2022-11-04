package stackoverflow.domain.account.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.account.dto.ProfileAccountResDto;
import stackoverflow.domain.account.dto.LoginAccountResDto;
import stackoverflow.domain.account.dto.PatchAccountReqDto;
import stackoverflow.domain.account.dto.PostAccountReqDto;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.service.AccountService;
import stackoverflow.global.argumentreslover.LoginAccountId;
import stackoverflow.global.common.dto.SingleResDto;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<SingleResDto<String>> accountAdd(@Valid @ModelAttribute PostAccountReqDto createAccountReqDto) {
        createAccountReqDto.setPassword(passwordEncoder.encode(createAccountReqDto.getPassword()));

        accountService.addAccount(createAccountReqDto);

        return new ResponseEntity<>(new SingleResDto<>("success create account"), HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<ProfileAccountResDto> accountProfileDetails(@PathVariable long accountId) {
        ProfileAccountResDto profileAccountResDto = accountService.findProfileAccount(accountId);


        return new ResponseEntity(profileAccountResDto, HttpStatus.OK);
    }

    @PostMapping("/{accountId}")
    public ResponseEntity<SingleResDto<String>> accountModify(@PathVariable long accountId,
                                                              @Valid @ModelAttribute PatchAccountReqDto modifyAccountReqDto,
                                                              @LoginAccountId Long loginAccountId) {
        //패스워드가 존재하면 암호화해서 넣는 로직
        Optional.ofNullable(modifyAccountReqDto.getPassword())
                        .ifPresent(password -> modifyAccountReqDto.setPassword(passwordEncoder.encode(password)));

        Account findAccount = accountService.findAccount(accountId);
        accountService.verifyAuthority(findAccount, loginAccountId);

        accountService.modifyAccount(findAccount, modifyAccountReqDto);

        return new ResponseEntity<>(new SingleResDto<>("success modify account"), HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<SingleResDto<String>> accountRemove(@PathVariable long accountId,
                                                              @LoginAccountId Long loginAccountId) {
        Account findAccount = accountService.findAccount(accountId);
        accountService.verifyAuthority(findAccount, loginAccountId);
        accountService.removeAccount(findAccount);

        return new ResponseEntity<>(new SingleResDto<>("success delete account"), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<LoginAccountResDto> accountUserDetails(@LoginAccountId Long loginAccountId) {
        Account account = accountService.findAccount(loginAccountId);

        return new ResponseEntity<>(new LoginAccountResDto(account), HttpStatus.OK);
    }
}
