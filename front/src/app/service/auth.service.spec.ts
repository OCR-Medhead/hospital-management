import { TestBed } from '@angular/core/testing';
import { AuthService } from './auth.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { of } from 'rxjs';

describe('AuthService', () => {
    let httpClientSpy: jasmine.SpyObj<HttpClient>;
    let authService: AuthService;

    beforeEach(() => {
        jasmine.DEFAULT_TIMEOUT_INTERVAL = 10000
        TestBed.configureTestingModule({
            imports: [HttpClientModule],
            providers: [HttpClientModule],
        })

        httpClientSpy = jasmine.createSpyObj('HttpClient', ['post', 'get']);
        authService = new AuthService(httpClientSpy);

    });

    it('should be created', () => {
        expect(authService).toBeTruthy();
    });

    it("Login should failed", async () => {
        httpClientSpy.post.and.returnValue(of({status:403}))      

        let reponse = new Promise<boolean>(async (resolve, _) => {
            resolve(await authService.login("zdazd", "azd"))
        })
        expect(reponse).not.toBeTrue()
    })

    it("Login should success", (done) => {
        httpClientSpy.post.and.returnValue(of("eyhbzfzebnfi"))

        authService.login("dev@dev.fr", "123").then((value) => {
            expect(value).toBeTrue()

            let token = authService.loadTokenInStorage()
            expect(token).toEqual("eyhbzfzebnfi")
            done()
        })

    })

});
