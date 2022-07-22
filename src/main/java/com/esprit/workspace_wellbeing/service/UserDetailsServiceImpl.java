package com.esprit.workspace_wellbeing.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esprit.workspace_wellbeing.repository.RoleRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;
import com.esprit.workspace_wellbeing.security.jwt.request.SignUpForm;
import com.esprit.workspace_wellbeing.security.jwt.response.ResponseMessage;
import com.esprit.workspace_wellbeing.entity.Role;
import com.esprit.workspace_wellbeing.entity.User;
import com.esprit.workspace_wellbeing.enums.RoleName;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or mail : " + username));

		return UserPrinciple.build(user);
	}

	@Transactional
	public void deleteByUsername(String username) throws NotFoundException {
		Optional<User> userOpt = userRepository.findByUsername(username);
		if (!userOpt.isPresent()) {
			throw new NotFoundException();
		}
		userRepository.deleteByUsername(username);
	}

	@Transactional
	public User updateUser(SignUpForm userToUpdate) throws NotFoundException {
		Optional<User> userOpt = userRepository.findByUsername(userToUpdate.getUsername());
		if (!userOpt.isPresent()) {
			throw new NotFoundException();
		}
		User user = userOpt.get();

		user.setActif(userToUpdate.getActif());
		user.setFirstName(userToUpdate.getFirstName());
		user.setBirthday(userToUpdate.getBirthday());
		user.setLastName(userToUpdate.getLastName());
		user.setMail(userToUpdate.getMail());
		user.setMatricule(userToUpdate.getMatricule());
		user.setPassword(userToUpdate.getPassword());
		user.setTypeContract(userToUpdate.getTypeContract());
		user.setStartingDate(userToUpdate.getStartingDate());
		user.setProfilePicture(userToUpdate.getProfilePicture());

        Set<String> strRoles = userToUpdate.getRole();

        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
                    roles.add(adminRole);

                    break;
                case "superadmin":
                    Role companyRole = roleRepository.findByName(RoleName.ROLE_SUPER_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
                    roles.add(companyRole);

                    break;

                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_EMPLOYEE)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
		return userRepository.save(user);

	}
};