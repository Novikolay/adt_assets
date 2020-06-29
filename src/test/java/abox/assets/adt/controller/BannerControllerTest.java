package abox.assets.adt.controller;

import abox.assets.adt.service.BannerService;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

class BannerControllerTest {

    private BannerController bannerController;

    private final BannerService bannerService;


    BannerControllerTest(BannerController bannerController) {
        this.bannerController = bannerController;
        this.bannerService = bannerService;
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before CalculatorTest.class");
    }

    @AfterClass
    public  static void afterClass() {
        System.out.println("After CalculatorTest.class");
    }

    @Before
    public void initTest() {
        bannerController = new BannerController(bannerService);
    }

    @After
    public void afterTest() {
        bannerController = null;
    }

    @Test
    void getBanner() {
        return bannerService.getOne(bannerID);
    }

    @Test
    void getBannerMain() {
    }

    @Test
    void getBannerComplex() {
    }

    @Test
    void bannerMain() {
    }

    @Test
    void bannerInfo() {
    }

    @Test
    void updateBannerFileMain() {
    }

    @Test
    void updateBannerFileInfo() {
    }
}