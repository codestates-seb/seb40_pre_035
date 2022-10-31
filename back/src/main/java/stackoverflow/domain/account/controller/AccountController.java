package stackoverflow.domain.account.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.account.dto.AccountResDto;
import stackoverflow.domain.account.dto.PostAccountReqDto;
import stackoverflow.domain.account.dto.PatchAccountReqDto;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.account.service.AccountService;
import stackoverflow.global.common.dto.SingleResDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;


    @PostMapping
    public ResponseEntity<SingleResDto<String>> accountAdd(@Valid @RequestBody PostAccountReqDto createAccountReqDto) {
        createAccountReqDto.setPassword(passwordEncoder.encode(createAccountReqDto.getPassword()));
        Account account = createAccountReqDto.toAccount();
        Account defaultAccount = accountService.setDefaultProperties(account);

        accountService.addAccount(defaultAccount);

        return new ResponseEntity<>(new SingleResDto<>("success create account"), HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResDto> accountDetails(@PathVariable long accountId) {

        // DB에서 회원 찾는 로직

        AccountResDto mockResDto = new AccountResDto("mock@gmail.com", "Mock nickname", "/mock/path");

        return new ResponseEntity(mockResDto, HttpStatus.OK);
    }

    @PatchMapping("/{accountId}")
    public ResponseEntity<SingleResDto<String>> accountModify(@PathVariable long accountId, @RequestBody PatchAccountReqDto modifyAccountReqDto) {

        // 회원 정보 수정하는 로직
        // 현재는 requestDto 에 별명, 프로필 경로만 추가

        return new ResponseEntity<>(new SingleResDto<>("success modify account"), HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<SingleResDto<String>> accountRemove(@PathVariable long accountId) {

        //회원 정보 삭제하는 로직

        return new ResponseEntity<>(new SingleResDto<>("success delete account"), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<AccountResDto> accountUserDetails(@AuthenticationPrincipal User user) {
        // 현재 로그인한 회원 정보 조회
        //@AuthenticationPrincipal 은 UserDetailsService 에서 리턴한 객체를 파라미터로 받을 수 있음

        return new ResponseEntity<>(new AccountResDto("user@gmail.com", "login user", "/path/user"), HttpStatus.OK);
    }
}
