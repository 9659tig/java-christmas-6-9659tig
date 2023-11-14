package christmas;

import domain.Menu;
import view.InputView;

import java.util.List;


public class Application {
    public static void main(String[] args) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        InputView inputView = new InputView();
        int date = inputView.readDate();

        List<Menu> menus = inputView.readMenus();
    }
}
