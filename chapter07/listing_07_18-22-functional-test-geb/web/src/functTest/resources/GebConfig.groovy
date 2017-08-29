import org.openqa.selenium.firefox.FirefoxDriver

waiting {
    timeout = 4
}

environments {
    firefox {
        driver = { new FirefoxDriver() }
    }
}
