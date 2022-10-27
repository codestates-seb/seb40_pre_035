package stackoverflow.domain.account.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.account.dto.AccountResDto;
import stackoverflow.domain.account.dto.PostAccountReqDto;
import stackoverflow.domain.account.dto.PatchAccountReqDto;
import stackoverflow.global.common.dto.SingleResDto;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @PostMapping
    public ResponseEntity<SingleResDto<String>> accountAdd(@RequestBody PostAccountReqDto createAccountReqDto) {

        //DB에 회원 정보를 저장하는 로직

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


}
