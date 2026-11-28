package com.smartmetro.service;

import com.smartmetro.entity.MetroCard;
import com.smartmetro.entity.User;
import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.MetroCardTO;
import com.smartmetro.model.UserRequest;
import com.smartmetro.model.UserTO;
import com.smartmetro.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserTO> findAll() throws UserNotFoundException {
        log.info("Inside the UserServiceImpl.findAll");
        List<User> users = userRepository.findAll();

        if(CollectionUtils.isEmpty(users)){
            log.error("User not found");
            throw new UserNotFoundException("User not found");
        }

        List<UserTO> userTOS = users.stream().map(user -> {
            UserTO userTO = new UserTO();
            userTO.setUserId(user.getUserId());
            userTO.setName(user.getName());
            userTO.setRole(user.getRole());
            userTO.setEmail(user.getEmail());
            userTO.setPhone(user.getPhone());

            Set<MetroCard> metroCards = user.getMetroCards();
            if(!CollectionUtils.isEmpty(metroCards)) {
                List<MetroCardTO> metroCardTOS = metroCards.stream().map(metroCard -> {
                    MetroCardTO metroCardTO = new MetroCardTO();
                    metroCardTO.setCardNumber(metroCard.getCardNumber());
                    metroCardTO.setCardId(metroCard.getCardId());
                    metroCardTO.setBalance(metroCard.getBalance());
                    metroCardTO.setStatus(metroCard.getStatus());
                    return metroCardTO;
                }).toList();

                userTO.setMetroCardTOList(metroCardTOS);
            }
            return userTO;
        }).toList();

        log.info("End of UserServiceImpl.findAll");
        return userTOS;
    }
}
