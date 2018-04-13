public class VolvoFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Volvo();
    }
}
