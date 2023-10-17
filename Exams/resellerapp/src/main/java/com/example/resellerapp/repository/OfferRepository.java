package com.example.resellerapp.repository;

import com.example.resellerapp.model.dto.OfferViewDto;
import com.example.resellerapp.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findAllByCreatorId(Long creatorId);

    List<Offer> findAllByBuyerId(Long currentUserId);

    List<Offer> findAllByCreatorIdNotAndBuyerIsNull(Long currentUserId);
}
