package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.UserEvent;
import com.example.demo1_pbl4.model.dto.MemberInRating;
import com.example.demo1_pbl4.repository.RatingEventRepository;
import com.example.demo1_pbl4.service.RatingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingEventServiceImpl implements RatingEventService {
    @Autowired
    private RatingEventRepository ratingEventRepository;

    @Override
    public List<Rating> getAllRatings() {
        return ratingEventRepository.findAll();
    }

    @Override
    public Rating getRatingById(Long ratingId) {
        return ratingEventRepository.findById(ratingId).get();
    }

    @Override
    public Rating insertRating(Rating ratingEvent) {
        return ratingEventRepository.save(ratingEvent);
    }

    @Override
    public void updateRating(Rating ratingEvent) {
        ratingEventRepository.save(ratingEvent);
    }

    @Override
    public boolean deleteRating(Long ratingId) {
        ratingEventRepository.deleteById(ratingId);
        return true;
    }

    @Override
    public List<Rating> findRatingByUserId(Long UserId) {
        return ratingEventRepository.findRatingByUserId(UserId);
    }


    @Override
    public List<MemberInRating> findMemberInEvent(Long eventId, String role) {
        List<MemberInRating> members = new ArrayList<>();
        for (UserEvent userEvent : ratingEventRepository.findMemberInEvent(eventId, role)) {
            MemberInRating member = new MemberInRating();
            User user= ratingEventRepository.findUserInEvent(userEvent.getUser().getUserId());
            Rating rating =ratingEventRepository.findRatingInEvent(user.getUserId(),userEvent.getEvent().getEventId());
            member.setUserId(userEvent.getUser().getUserId());
            member.setFirstName(user.getFirstName());
            member.setLastName(user.getLastName());
            member.setEventRole(userEvent.getEventRole());
            member.setPoint4(rating.getPoint4());
            member.setPoint5(rating.getPoint5());
            member.setPoint6(rating.getPoint6());
            System.out.println("\n first name: "+member.getFirstName());
            members.add(member);
        }
        return members;
    }

}
