package view;

import mapping.dto.ToyDto;
import mapping.enums.ToyType;
import services.ToyService;
import services.impl.ToyServiceImpl;

import java.util.List;

public class MainToy {
    public static void main(String[] args) {
        ToyService toyService = new ToyServiceImpl();
        toyService.addtoy(new ToyDto("Doll", ToyType.FEMALE,4.2,15));
        toyService.addtoy(new ToyDto("Baseball Ball", ToyType.MALE,15.2,13));
        toyService.addtoy(new ToyDto("Water Makeup", ToyType.FEMALE,5.5,10));
        toyService.addtoy(new ToyDto("Drawing Books", ToyType.UNISEX,2.5,9));
        toyService.addtoy(new ToyDto("Soccer Balls", ToyType.MALE,10.2,20));
        toyService.addtoy(new ToyDto("bubble Clock", ToyType.UNISEX,13.3,19));
        toyService.addtoy(new ToyDto("MaxStyle Gun", ToyType.MALE,6.2,30));
        toyService.addtoy(new ToyDto("Kitchen Toy", ToyType.FEMALE,1.2,22));

        List<ToyDto> toysOrdered = toyService.sortToysByStock();
        System.out.println("All toys = \n" + toysOrdered);

        ToyDto newToy = toyService.createNewToy("Puzzle",ToyType.UNISEX,11.1,14);
        System.out.println("\nNew agg toy: " + newToy);

        System.out.println("\nQuantity of female toys: " + toyService.getQuantityByType(ToyType.FEMALE));
        System.out.println("Quantity of male toys: " + toyService.getQuantityByType(ToyType.MALE));
        System.out.println("Quantity of unisex toys: " + toyService.getQuantityByType(ToyType.UNISEX));

        System.out.println("\nDecrease Stock");
        toyService.decreaseStocks("MaxStyle Gun",7);
        System.out.println("New quantity of MaxStyle Gun: " + toyService.getQuantityByType(ToyType.FEMALE));

        System.out.println("\nIncrease Stock:");
        toyService.increaseStock("Drawing Books: ",10);
        System.out.println("New quantity of Drawing Books: " + toyService.getQuantityByType(ToyType.UNISEX));

        System.out.println("\nType with more toys:  " + toyService.getTypeWithMoreToys());
        System.out.println("Type with less toys: " + toyService.getTypeWithLessToys());

        List<ToyDto> toysWithValueGreaterA10 = toyService.getToysWithValueGreaterA(10.0);
        System.out.println("\ntoys with value greater than 10 = " + toysWithValueGreaterA10);

        List<ToyDto> toysOrdered2 = toyService.sortToysByStock();
        System.out.println("\ntoys ordered = " + toysOrdered2);
        System.out.println("\nTotal quantity of toys: "+ toyService.getTotalAmount());
        System.out.println("Total value of all toys: $"+ toyService.getTotalValue());
    }

}
