package com.msladkov.databasecoursework.service;

import com.msladkov.databasecoursework.dao.*;
import com.msladkov.databasecoursework.dto.*;
import com.msladkov.databasecoursework.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SiteManagerService {
    private UserRepository userRepository;
    private SiteRepository siteRepository;
    private PlayRepository playRepository;
    private CompositionRepository compositionRepository;
    private SeasonRepository seasonRepository;
    private TopicRepository topicRepository;
    private GenreRepository genreRepository;
    private LanguageRepository languageRepository;
    private ComposerRepository composerRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Autowired
    public void setPlayRepository(PlayRepository playRepository) {
        this.playRepository = playRepository;
    }

    @Autowired
    public void setCompositionRepository(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    @Autowired
    public void setSeasonRepository(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    @Autowired
    public void setTopicRepository(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Autowired
    public void setLanguageRepository(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Autowired
    public void setComposerRepository(ComposerRepository composerRepository) {
        this.composerRepository = composerRepository;
    }

    public boolean checkUserHasRights(AddPlayRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getLoginData().getEmail());
        if (user.isEmpty()) {
            return false;
        }
        if (user.get().getRole() == UserRole.ADMIN) {
            return true;
        }
        Long userId = user.get().getId();
        Optional<Site> siteBox = siteRepository.findById(request.getPlay().getSiteId());
        if (siteBox.isEmpty()) {
            return false;
        }
        Site site = siteBox.get();
        return site.getManager().getId().equals(userId);
    }

    public boolean checkUserHasRights(NoSpecificSiteManagerOrAdminRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getLoginData().getEmail());
        if (user.isEmpty()) {
            return false;
        }
        return user.get().getRole() != UserRole.USER;
    }

    public boolean checkUserHasRights(RemovePlayRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getLoginData().getEmail());
        if (user.isEmpty()) {
            return false;
        }
        Optional<Play> playBox = playRepository.findById(request.getPlayId());
        if (playBox.isEmpty()) {
            return false;
        }
        return playBox.get().getSite().getManager().getId().equals(user.get().getId());
    }

    public boolean addPlay(PlayRepresentation playRepresentation) {
        Optional<Site> siteBox = siteRepository.findById(playRepresentation.getSiteId());
        Optional<Composition> compositionBox = compositionRepository.findById(playRepresentation.getCompositionId());
        Optional<Season> seasonBox = seasonRepository.findById(playRepresentation.getSeasonId());
        if (siteBox.isEmpty() || compositionBox.isEmpty() || seasonBox.isEmpty()) {
            return false;
        }
        Play toSave = new Play(playRepresentation, seasonBox.get(), compositionBox.get(), siteBox.get());
        playRepository.save(toSave);
        return true;
    }

    public boolean addComposition(CompositionRepresentation compositionRepresentation) {
        List<Topic> topicList = new LinkedList<>();
        for (String topicName : compositionRepresentation.getTopics()) {
            Optional<Topic> maybeTopic = topicRepository.findByName(topicName);
            if (maybeTopic.isPresent()) {
                topicList.add(maybeTopic.get());
            } else return false;
        }
        Optional<Genre> genreBox = genreRepository.findByName(compositionRepresentation.getGenre());
        Optional<Language> languageBox = languageRepository.findByName(compositionRepresentation.getLanguage());
        Optional<Composer> composerBox = composerRepository.findById(compositionRepresentation.getComposerId());
        if (genreBox.isEmpty() || languageBox.isEmpty() || composerBox.isEmpty()) {
            return false;
        }
        Composition toSave = new Composition(
                compositionRepresentation,
                topicList,
                languageBox.get(),
                composerBox.get(),
                genreBox.get()
        );
        compositionRepository.save(toSave);
        return true;
    }

    public void addComposer(ComposerRepresentation composerRepresentation) {
        composerRepository.save(new Composer(composerRepresentation));
    }

    public void removePlay(Long id) {
        playRepository.deleteById(id);
    }
}
