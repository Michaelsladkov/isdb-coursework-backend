package com.msladkov.databasecoursework.service;

import com.msladkov.databasecoursework.dao.*;
import com.msladkov.databasecoursework.dto.AddPlanRequest;
import com.msladkov.databasecoursework.dto.AddWishRequest;
import com.msladkov.databasecoursework.dto.NotificationRepresentation;
import com.msladkov.databasecoursework.dto.UserLoginData;
import com.msladkov.databasecoursework.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserActionsService {
    private AuthenticationService authenticationService;
    private CompositionRepository compositionRepository;
    private PlayRepository playRepository;
    private NotificationRepository notificationRepository;
    private UserRepository userRepository;
    private WishRepository wishRepository;
    private PlanRepository planRepository;

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Autowired
    public void setCompositionRepository(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    @Autowired
    public void setPlayRepository(PlayRepository playRepository) {
        this.playRepository = playRepository;
    }

    @Autowired
    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setWishRepository(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    @Autowired
    public void setPlanRepository(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<NotificationRepresentation> showNotifications(UserLoginData loginData) {
        Optional<User> maybeUser = userRepository.findByEmail(loginData.getEmail());
        List<NotificationRepresentation> ret = new LinkedList<>();
        if (maybeUser.isEmpty()) {
            return ret;
        }
        List<Notification> notificationList = notificationRepository.showNotificationsByUserId(maybeUser.get().getId());
        for (Notification n : notificationList) {
            Play play = playRepository.findById(n.getPlay()).get();
            Long playId = null;
            StringBuilder caption = new StringBuilder();
            if (n.getType() == NotificationType.PLANNED_PLAY) {
                caption.append("You were interested in composition: ")
                        .append(play.getComposition().getName())
                        .append(". ");
                caption.append("It is being played in play: ")
                        .append(play.getName())
                        .append(" ")
                        .append("in ")
                        .append(play.getSite().getName());
                playId = play.getId();
            }
            if (n.getType() == NotificationType.CANCELED_PLAY) {
                caption.append("Play ")
                        .append(play.getName())
                        .append(" in ")
                        .append(play.getSite())
                        .append(" is canceled. We are sorry");
                Long userId = maybeUser.get().getId();
                playId = play.getId();
                planRepository.deleteById(new UserPlannedKey(userId, playId));
            }
            if (n.getType() == NotificationType.HIGH_INTEREST) {
                caption.append("A lot of people are interested in play ")
                        .append(play.getName())
                        .append(" in ")
                        .append(play.getSite().getName());
                playId = play.getId();
            }
            ret.add(new NotificationRepresentation(caption.toString(), playId));
        }
        return ret;
    }

    public boolean addWish(AddWishRequest request) {
        Optional<Composition> mayBeComposition = compositionRepository.findById(request.getCompositionId());
        if (mayBeComposition.isEmpty()) return false;
        User user = userRepository.findByEmail(request.getLoginData().getEmail()).get();
        Wishes wish = new Wishes(user, mayBeComposition.get());
        wishRepository.save(wish);
        return true;
    }

    public List<Wishes> getWishes(UserLoginData loginData) {
        User user = userRepository.findByEmail(loginData.getEmail()).get();
        return wishRepository.getAllByUserId(user.getId());
    }

    public boolean addPlan(AddPlanRequest request) {
        Optional<Play> mayBePlay = playRepository.findById(request.getPlayId());
        if (mayBePlay.isEmpty()) return false;
        User user = userRepository.findByEmail(request.getLoginData().getEmail()).get();
        PlannedPlay plannedPlay = new PlannedPlay(user, mayBePlay.get());
        planRepository.save(plannedPlay);
        return true;
    }

    public List<PlannedPlay> getPlans(UserLoginData loginData) {
        User user = userRepository.findByEmail(loginData.getEmail()).get();
        return planRepository.getAllByUserId(user.getId());
    }
}
