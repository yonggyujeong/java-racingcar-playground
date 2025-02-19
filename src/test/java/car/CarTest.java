package car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CarTest {

    @Test
    void 이름_5자_이하_정상생성_Test() throws Exception{
        Car car = new Car("5자입니다", 0);
    }

    @Test
    void 이름_5자_이상_예외발생_Test() throws Exception{
        assertThatThrownBy(() -> {
            Car car = new Car("5자이상입니다", 0);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차의 이름은 5자를 초과할 수 없습니다.");
    }

    @Test
    void 전진기능테스트_전진() throws Exception {
        Car car = new Car("poli", 0);
        car.drive(5);
        Integer distance = car.distance;

        assertThat(distance).isEqualTo(1);
    }

    @Test
    void 랜덤로직분리_Car() throws Exception{
        Car car = new Car("pori", 0) {
            @Override
            protected Integer makeRandomNumber() {
                return 5;
            }
        };
        car.drive();
        assertThat(car.getDistance()).isEqualTo(1);
    }

    @Test
    void 랜덤로직_인터페이스_분리_Strategy패턴_익명메서드() throws Exception {
        Car car = new Car("pori", 0);
        car.drive(new DriveStrategy() {
            @Override
            public Boolean movable() {
                return true;
            }
        });

        assertThat(car.getDistance()).isEqualTo(1);
    }

    @Test
    void 랜덤로직_인터페이스_분리_Strategy패턴_람다() throws Exception {
        Car car = new Car("pori", 0);
        car.drive(() -> true);

        assertThat(car.getDistance()).isEqualTo(1);
    }
}
