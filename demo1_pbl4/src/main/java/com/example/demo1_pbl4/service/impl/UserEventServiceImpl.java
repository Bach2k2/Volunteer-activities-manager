package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.UserEvent;
import com.example.demo1_pbl4.model.UserEventId;
import com.example.demo1_pbl4.model.dto.MemberInRating;
import com.example.demo1_pbl4.repository.UserEventRepository;
import com.example.demo1_pbl4.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserEventServiceImpl implements UserEventService {

    @Autowired
    private UserEventRepository userEventRepository;

    @Override
    public List<UserEvent> getAllUserEvents() {
        return userEventRepository.findAll();
    }

    @Override
    public UserEvent insertUser(UserEvent userEvent) {
        return userEventRepository.save(userEvent);

    }

    @Override
    public List<UserEvent> findMemberInEvent(Long eventId, String role) {
        List<UserEvent> userEventList = new ArrayList<>();
        for (UserEvent userEvent : userEventRepository.findAll()) {
            if (userEvent.getEvent().getEventId() == eventId && userEvent.getEventRole().equals(role)) {
                userEventList.add(userEvent);
            }
        }
        return userEventList;
    }

    @Override
    public List<UserEvent> findAllMemberInEvent(Long eventId) {
        List<UserEvent> userEventList = new ArrayList<>();
        for (UserEvent userEvent : userEventRepository.findAll()) {
            if (userEvent.getEvent().getEventId() == eventId) {
                userEventList.add(userEvent);
            }
        }
        return userEventList;
    }

    @Override
    public List<UserEvent> findAllWaitingApproval(Long eventId) {
        List<UserEvent> userEventList = new ArrayList<>();
        for (UserEvent userEvent : userEventRepository.findAll()) {
            if (userEvent.getEvent().getEventId() == eventId && !userEvent.getApproval()) {
                userEventList.add(userEvent);
            }
        }
        return userEventList;
    }

    @Override
    public UserEvent getUserEventById(Long userId, Long eventId) {
        return userEventRepository.findById(new UserEventId(userId, eventId)).get();
    }
}
