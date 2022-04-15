import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {
        Usb usb = new Usb("1.0");
        Soundcard soundcard = new Soundcard(usb);
        Optional<Computer> computer = Optional.of(new Computer(soundcard));
        System.out.println(usb); // Usb{version='1.0'}
        System.out.println(soundcard); // Soundcard{usb=Optional[Usb{version='1.0'}]}
        System.out.println(computer); // Optional[Computer{soundcard=Optional[Soundcard{usb=Optional[Usb{version='1.0'}]}]}]
        // 1
        System.out.println(computer.flatMap(Computer::getSoundcard)); // Optional[Soundcard{usb=Optional[Usb{version='1.0'}]}]
        // 2
        System.out.println(computer.map(Computer::getSoundcard)); // Optional[Optional[Soundcard{usb=Optional[Usb{version='1.0'}]}]]
        // 3
        System.out.println(computer.flatMap(Computer::getSoundcard).flatMap(Soundcard::getUsb));
        // 4
        Optional<Usb> extractedUsb = computer
                .flatMap(Computer::getSoundcard)
                .flatMap(Soundcard::getUsb);
        extractedUsb.ifPresent(System.out::println); // Optional[Usb{version='1.0'}]
        // 5
        computer.flatMap(Computer::getSoundcard)
                .flatMap(Soundcard::getUsb)
                .ifPresent(System.out::println); // Optional[Usb{version='1.0'}]
        // 6
        computer.flatMap(Computer::getSoundcard)
                .flatMap(Soundcard::getUsb)
                .map(Usb::version)
                .ifPresent(System.out::println); // 1.0
        // 7
        String version = computer
                .flatMap(Computer::getSoundcard)
                .flatMap(Soundcard::getUsb)
                .map(Usb::version)
                .orElse("n.a.");
        System.out.println(version); // 1.0

        // 8
        Soundcard soundcard2 = new Soundcard(new Usb("2.0"));
//        Soundcard soundcard2 = new Soundcard(null);
        System.out.println(soundcard2.getUsb().map(Usb::version).orElse("n.a.")); // 2.0 (or n.a.)

        // 9
        soundcard2
                .getUsb()
                .map(Usb::version)
                .ifPresentOrElse(System.out::println, () -> System.out.println("n.a.")); // 2.0 (or n.a.)
    }
}


class Computer {
    private final Optional<Soundcard> soundcard;

    public Computer(Soundcard soundcard) {
        this.soundcard = Optional.ofNullable(soundcard);
    }

    public Optional<Soundcard> getSoundcard() {
        return soundcard;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "soundcard=" + soundcard +
                '}';
    }
}

class Soundcard {
    private final Optional<Usb> usb;

    public Soundcard(Usb usbOpt) {
        this.usb = Optional.ofNullable(usbOpt);
    }

    public Optional<Usb> getUsb() {
        return usb;
    }

    @Override
    public String toString() {
        return "Soundcard{" +
                "usb=" + usb +
                '}';
    }
}

record Usb(String version) {

    @Override
    public String toString() {
        return "Usb{" +
                "version='" + version + '\'' +
                '}';
    }
}
