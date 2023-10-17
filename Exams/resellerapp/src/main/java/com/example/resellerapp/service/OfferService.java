package com.example.resellerapp.service;

import com.example.resellerapp.model.dto.OfferViewDto;
import com.example.resellerapp.model.entity.Offer;
import com.example.resellerapp.model.entity.User;
import com.example.resellerapp.model.service.OfferServiceModel;
import com.example.resellerapp.repository.OfferRepository;
import com.example.resellerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final CurrentUser currentUser;

    private final ConditionService conditionService;

    public OfferService(OfferRepository offerRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, ConditionService conditionService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.conditionService = conditionService;
    }

    public void addProduct(OfferServiceModel offerServiceModel) {
        Offer offer = modelMapper.map(offerServiceModel, Offer.class);
        offer.setCreator(userService.findById(currentUser.getId()));
        offer.setCondition(conditionService.findByConditionNameEnum(offerServiceModel.getCondition()));

        offerRepository.save(offer);
    }

    public List<OfferViewDto> myOffers(Long currentUserId) {
        return offerRepository.findAllByCreatorId(currentUserId)
                .stream()
                .map(offer -> modelMapper.map(offer, OfferViewDto.class))
                .collect(Collectors.toList());
    }

    public List<OfferViewDto> boughtItems(Long currentUserId) {
        return offerRepository.findAllByBuyerId(currentUserId)
                .stream()
                .map(offer -> modelMapper.map(offer, OfferViewDto.class))
                .collect(Collectors.toList());

    }


    public List<OfferViewDto> otherOffers(Long currentUserId) {
        return offerRepository.findAllByCreatorIdNotAndBuyerIsNull(currentUserId)
                .stream()
                .map(offer -> modelMapper.map(offer, OfferViewDto.class))
                .collect(Collectors.toList());
    }

    public void buyOfferWithId(Long offerId) {
        User user = userService.findById(currentUser.getId());

        Optional<Offer> optionalOffer = offerRepository.findById(offerId);
        optionalOffer.ifPresent(offer -> offer.setBuyer(user));
        List<Offer> boughtItems = offerRepository.findAllByBuyerId(currentUser.getId());
        optionalOffer.ifPresent(boughtItems::add);

        Long sellerId = optionalOffer.get().getCreator().getId();
        User seller = userService.findById(sellerId);
        List<Offer> createdOffersBySeller = offerRepository.findAllByCreatorId(seller.getId());
        optionalOffer.ifPresent(createdOffersBySeller::remove);

        offerRepository.save(optionalOffer.get());
    }

    public void removeOfferById(Long id) {
        this.offerRepository.deleteById(id);
    }
}
