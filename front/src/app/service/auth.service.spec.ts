import { TestBed, fakeAsync, flush, tick } from '@angular/core/testing';
import { AuthService } from './auth.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpClientModule } from '@angular/common/http';

describe('AuthService', () => {
    let service: AuthService;

    beforeEach(() => {
        jasmine.DEFAULT_TIMEOUT_INTERVAL = 10000
        TestBed.configureTestingModule({
            imports: [HttpClientModule],
            providers: [HttpClientModule],
        })
        service = TestBed.inject(AuthService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    it("Login should failed", () => {
        console.log("test")

        console.debug("debug test")
        let reponse = new Promise<boolean>(async (resolve, reject) => {
            resolve(await service.login("zdazd", "azd"))
        })
        expect(reponse).not.toBeTrue()
    })

    it("Login should success", (done) => {
        service.login("dev@dev.fr", "123").then((value) => {
            expect(value).toBeTrue()

            let token = service.loadTokenInStorage()
            expect(token).not.toEqual("")
            done()
        })

    })

});
