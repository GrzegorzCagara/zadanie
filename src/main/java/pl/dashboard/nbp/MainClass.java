package pl.dashboard.nbp;

import pl.dashboard.nbp.service.*;

import java.util.stream.Stream;

public class MainClass {


    public static void main(String[] args) {
        InputValidator inputValidator = new InputValidatorImpl();
        ConnectionService connectionService = new ConnectionServiceImpl();
        EntityToJsonService entityToJsonService = new EntityToJsonServiceImpl(connectionService);
        PrintService printService = new PrintServiceImpl(connectionService, entityToJsonService);

        if (!inputValidator.validData(args)) {
            System.out.print("Błędnie podany parametr wejściowy: ");
            Stream.of(args).forEach(e -> System.out.print(" [" + e + "] "));
            return;
        }
        String data = args[0];

        printService.printMessage(data);
    }
}
