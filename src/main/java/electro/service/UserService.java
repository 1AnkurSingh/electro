package electro.service;

import electro.model.User;
import electro.model.userDto.UserDto;
import electro.repository.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Builder
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto  addSingleUser( UserDto userDto){
        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        if(existingUser.isPresent()){
        return null;


    }else
        {
            User user = dtoToEntity(userDto);
            User save1 = userRepository.save(user);

            return entityToDto(save1);
        }

    }
    private  User dtoToEntity(UserDto userDto){
        User user=User.builder()
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword())
                .inviteCode(userDto.getInviteCode()).build();
        return user;
    }
    private UserDto entityToDto(User savedUser){
        UserDto build= UserDto.builder()
                .phoneNumber(savedUser.getPhoneNumber())
                .password(savedUser.getPassword())
                .inviteCode(savedUser.getInviteCode()).build();
        return build;
    }

    // login user
//    public String loginUser(UserDto userDto){
//        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
//        Optional<User> existingPassword = userRepository.findByPassword(userDto.getPassword());
//        System.out.println(existingPassword);
//        if(existingUser.isPresent()&& existingPassword.isPresent()){
//            return "User is present login successful";
//        } else if (existingUser.isPresent() && !existingPassword.isPresent()) {
//            return "incorrect password";
//
//        }else {
//            return "Create account";
//
//        }
//    }

    public String loginUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(userDto.getPassword())) {
            return "User is present, login successful";
        } else if (existingUser.isPresent()) {
            return "Incorrect password";
        } else {
            return "Create account";
        }
    }


}
