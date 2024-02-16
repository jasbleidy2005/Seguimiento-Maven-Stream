package services;

import mapping.dto.ToyDto;
import mapping.enums.ToyType;

import java.util.List;

public interface ToyService {
    ToyDto createNewToy(String name, ToyType type, double price, int quantities);
    void addtoy(ToyDto toyDto);
        int getQuantityByType(ToyType type);
        int getTotalAmount();
        double getTotalValue();
        void decreaseStocks(String nameToy, int quantities);
        void increaseStock(String nameToy, int quantities);
        ToyType getTypeWithMoreToys();
        ToyType getTypeWithLessToys();
        List<ToyDto> getToysWithValueGreaterA(double valor);
        List<ToyDto> sortToysByStock();
    }

