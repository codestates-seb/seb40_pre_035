package stackoverflow.domain.account.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stackoverflow.domain.account.dto.AccountResDto;
import stackoverflow.domain.account.dto.PostAccountReqDto;
import stackoverflow.domain.account.dto.PatchAccountReqDto;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @PostMapping
    public String postAccount(@RequestBody PostAccountReqDto createAccountReqDto) {

        //DB에 회원 정보를 저장하는 로직

        return "Create Success";
    }

    @GetMapping("/{account-id}")
    public ResponseEntity getAccount(@PathVariable("account-id") long accountId) {

        // DB에서 회원 찾는 로직

        AccountResDto mockResDto = new AccountResDto("mock@gmail.com", "Mock nickname", "/mock/path");

        return new ResponseEntity(mockResDto, HttpStatus.OK);
    }

    @PatchMapping("/{account-id}")
    public String patchAccount(@PathVariable("account-id") long accountId, @RequestBody PatchAccountReqDto modifyAccountReqDto) {

        // 회원 정보 수정하는 로직
        // 현재는 requestDto 에 별명, 프로필 경로만 추가

        return "Modify Success";
    }

    @DeleteMapping("/{account-id}")
    public String deleteAccount(@PathVariable("account-id") long accountId) {

        //회원 정보 삭제하는 로직

        return "Delete Success";
    }


}
