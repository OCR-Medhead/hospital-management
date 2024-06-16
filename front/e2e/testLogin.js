const { Builder, By, until, Browser } = require("selenium-webdriver");
const assert = require("assert");

(async function loginTest() {

    let driver = await new Builder().forBrowser("chrome").build()

    try {
        await driver.manage().setTimeouts({ implicit: 1000})
        await driver.get("http://localhost:80")
        let pageTitle = await driver.getTitle()
        
        assert.strictEqual(pageTitle, "HospitalManagement")

        await driver.findElement(By.id("email")).sendKeys("dev@dev.fr")
        await driver.findElement(By.id("password")).sendKeys("123")

        await driver.findElement(By.id("loginButton")).click();

        await driver.wait(until.urlContains("search"), 4000)

        let searchTitle = await driver.findElement(By.className("display-6")).getText()
        assert.strictEqual(searchTitle, "Rechercher un hopital")

        console.log("Tests success")
    }
    catch(ex){
        console.log("Tests failed")
        console.log(ex)
    }
    finally {
        await driver.quit()
    }

})();