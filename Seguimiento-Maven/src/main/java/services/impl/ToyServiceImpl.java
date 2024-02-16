package services.impl;

import mapping.dto.ToyDto;
import mapping.enums.ToyType;
import mapping.mappers.ToyMapper;
import model.Toy;
import services.ToyService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ToyServiceImpl implements ToyService {
    private List<Toy> toy  = new ArrayList<>();

    @Override
    public ToyDto createNewToy(String name, ToyType type, double price, int quantities) {
        Toy toy1 = new Toy(name, type, price, quantities);
        toy.add(toy1);
        return ToyMapper.mapToDTO(toy1);
    }
    @Override
    public void addtoy(ToyDto toyDto) {
        Toy toy1 = new Toy(toyDto.name(),toyDto.type(),toyDto.price(),toyDto.quantities());
        toy.add(toy1);
    }

    @Override
    public int getQuantityByType(ToyType type) {
        return (int) toy.stream().filter(toy1->toy1.getType()==type).count();
    }
    @Override
    public int getTotalAmount() {
        return toy.stream().mapToInt(Toy::getQuantities).sum();
    }

    @Override
    public double getTotalValue(){
        return toy.stream().mapToDouble(toy1->toy1.getPrice()*toy1.getQuantities()).sum();
    }
    @Override
    public void decreaseStocks(String nameToy, int quantities) {
        toy.stream()
                .filter(toy1 -> toy1.getName().equals(nameToy))
                .findFirst().ifPresent(toy1 -> toy1.setQuantities(toy1.getQuantities()-quantities));
    }

    @Override
    public void increaseStock(String nameToy, int quantities) {
        toy.stream().filter(toy1 -> toy1.getName().equals(nameToy))
                .findFirst().ifPresent(toy1 -> toy1.setQuantities(toy1.getQuantities()+quantities));
    }

    @Override
    public ToyType getTypeWithMoreToys() {
        return toy.stream().collect(Collectors.groupingBy(Toy::getType,Collectors.summingInt(Toy::getQuantities)))
                .entrySet().stream()
                .max(Comparator.comparingInt(toy1->toy1.getValue()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    @Override
    public ToyType getTypeWithLessToys() {
        return toy.stream().collect(Collectors.groupingBy(Toy::getType,Collectors.summingInt(Toy::getQuantities)))
                .entrySet().stream()
                .min(Comparator.comparingInt(toy1->toy1.getValue()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    @Override
    public List<ToyDto> getToysWithValueGreaterA(double valor) {
        return toy.stream()
                .filter(toy1 -> toy1.getPrice()>valor)
                .map(ToyMapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ToyDto> sortToysByStock() {
        return toy.stream()
                .sorted(Comparator.comparingInt(Toy::getQuantities))
                .map(ToyMapper::mapToDTO).collect(Collectors.toList());
    }
    }

