package com.example.battleships.service;

import com.example.battleships.model.dto.ShipDto;
import com.example.battleships.model.dto.StartBattleDto;
import com.example.battleships.model.entity.Ship;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.repository.ShipRepository;
import com.example.battleships.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private final ShipRepository shipRepository;

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final CurrentUser currentUser;

    private final CategoryService categoryService;

    public ShipService(ShipRepository shipRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, CategoryService categoryService) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
    }

    public void addShip(ShipServiceModel shipServiceModel) {
        Ship ship = modelMapper.map(shipServiceModel, Ship.class);
        ship.setUser(userService.findById(currentUser.getId()));
        ship.setCategory(categoryService.findByCategoryEnum(shipServiceModel.getCategory()));

        shipRepository.save(ship);
    }

    public List<ShipDto> getOwnedShips(long ownerId) {
        return shipRepository.findByUserId(ownerId)
                .stream()
                .map(ShipDto::new)
                .collect(Collectors.toList());
    }

    public List<ShipDto> getNotOwnedShips(long ownerId) {
        return shipRepository.findByUserIdNot(ownerId)
                .stream()
                .map(ShipDto::new)
                .collect(Collectors.toList());
    }

    public List<ShipDto> getAllSortedShips() {
        return shipRepository.findByOrderByHealthAscNameDescPowerAsc()
                .stream()
                .map(ShipDto::new)
                .collect(Collectors.toList());
    }

    public void attack(StartBattleDto startBattleDto) {
        Optional<Ship> attackerOpt = shipRepository.findById(startBattleDto.getAttackerId());
        Optional<Ship> defenderOpt = shipRepository.findById(startBattleDto.getDefenderId());

        if (attackerOpt.isEmpty() || defenderOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        Ship attacker = attackerOpt.get();
        Ship defender = defenderOpt.get();

        long newDefenderHealth = defender.getHealth() - attacker.getPower();

        if (newDefenderHealth <= 0) {
            shipRepository.delete(defender);
        } else {
            defender.setHealth(newDefenderHealth);
            shipRepository.save(defender);
        }

    }
}
