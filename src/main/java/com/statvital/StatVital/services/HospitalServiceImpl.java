package com.statvital.StatVital.services;

//import com.statvital.StatVital.data.model.Confirmation;
import com.statvital.StatVital.config.AppConfig;
import com.statvital.StatVital.data.model.Child;
import com.statvital.StatVital.data.model.Death;
import com.statvital.StatVital.data.model.HospitalAdmin;
//import com.statvital.StatVital.data.repository.ConfirmationRepo;
//import com.statvital.StatVital.data.repository.ConfirmationRepo;
import com.statvital.StatVital.data.repository.ChildRepository;
import com.statvital.StatVital.data.repository.DeathRepo;
import com.statvital.StatVital.data.repository.HospitalAdminRepo;
import com.statvital.StatVital.data.repository.HospitalDeathRepo;
import com.statvital.StatVital.dtos.request.*;
import com.statvital.StatVital.dtos.response.*;
import com.statvital.StatVital.exceptions.*;
import com.statvital.StatVital.security.services.SecureUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import static com.statvital.StatVital.data.repository.ChildRepository.children;
//import static com.statvital.StatVital.data.repository.HospitalAdminRepo.children;
import static com.statvital.StatVital.utils.Mapper.*;

@Service
@AllArgsConstructor
public class HospitalServiceImpl implements HospitalService, UserDetailsService {
    private final HospitalAdminRepo hospitalAdminRepo;
    private final ChildRepository childRepository;
    private final ChildService childService;
    private final DeathService deathService;
    private final MailService mailService;
    private final AppConfig appConfig;
    private final DeathRepo deathRepo;
    private final HospitalDeathRepo hospitalDeathRepo;
//    private final JwtConfig jwtConfig;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

//    private final Connection connection;
//    private final JwtTokenImpl jwtToken;
//    private HospitalAdmin loggedIn;
//    private final ConfirmationRepo confirmationRepo;

    @Override
    public SignInHospitalAdminResponse signup(SignUpHospitalAdminRequest request) {
        findHospital(request.getEmail());
        mailService.sendMail(mailMapper(request));
        return responseMapper(hospitalAdminRepo.save(hospitalMapper(request)));


    }

    @Override
    public LogInAdminResponse logIn(SignInHospitalRequest request) {
        Optional<HospitalAdmin> hospitalAdmin = getAdmin(request.getEmail());
        if (hospitalAdmin.isEmpty()) {
            LogInAdminResponse logInAdminResponse = new LogInAdminResponse();
            logInAdminResponse.setMessage("Hospital Not Found");
            logInAdminResponse.setLogInDate(String.valueOf(LocalDateTime.now()));
            return logInAdminResponse;
        }
        if (!(bCryptPasswordEncoder.matches(request.getPassword(), hospitalAdmin.get().getPassword()))) {
            LogInAdminResponse logInAdminResponse = new LogInAdminResponse();
            logInAdminResponse.setMessage("Incorrect password");
            logInAdminResponse.setLogInDate(String.valueOf(LocalDateTime.now()));
            return logInAdminResponse;
//            throw new IllegalArgumentException("Incorrect password");
        }
//        String token = generateToken(hospitalAdmin.get().getEmail());
//        System.out.println(token);
        System.out.println(hospitalAdmin);
//        validatePassword(request, hospitalAdmin);

//        loggedIn = hospitalAdmin;

        hospitalAdmin.get().setLoggedIn(true);
        hospitalAdminRepo.save(hospitalAdmin.get());
        Optional<HospitalAdmin> hospitalAdmin1 = hospitalAdminRepo.findHospitalAdminByEmailIgnoreCase(request.getEmail());
//
//        String token = jwtToken.generateToken(hospitalAdmin1.getEmail(), hospitalAdmin1.getPassword());
//        String verifiedToken = jwtToken.verifyToken(String.valueOf(hospitalAdmin1.getEmail().equals(token)));

        HospitalAdmin hos = hospitalAdminRepo.saveAndFlush(hospitalAdmin.get());
//        Optional<HospitalAdmin> hospitalAdmin1 = hospitalAdminRepo.findHospitalAdminByEmailIgnoreCase(request.getEmail());
        //
        //        String token = jwtToken.generateToken(hospitalAdmin1.getEmail(), hospitalAdmin1.getPassword());
        //        String verifiedToken = jwtToken.verifyToken(String.valueOf(hospitalAdmin1.getEmail().equals(token)));
        System.out.println(hos);

        LogInAdminResponse logInAdminResponse = new LogInAdminResponse();
        logInAdminResponse.setMessage("Login Successful");
//        logInAdminResponse.setToken(token);
        logInAdminResponse.setLoggedIn(hospitalAdmin1.get().isLoggedIn());
        logInAdminResponse.setLogInDate(String.valueOf(LocalDateTime.now()));

        return logInAdminResponse;

    }
//    private String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getJwtDuration()))
//                .signWith(SignatureAlgorithm.HS512, jwtConfig.getJwtSecretKey())
//                .compact();
//    }




    @Override
    public RegisterChildResponse registerChild(ChildRequest childRequest) {
//        if (!isLoggedIn()) {
//            RegisterChildResponse response = new RegisterChildResponse();
//            response.setMessage("Kindly Login or SignUp.");
//            return response;
//        }

//        logIn(token);
//
//        ensureLoggedIn(token);
//        boolean isTokenValid = Boolean.parseBoolean(jwtToken.verifyToken(toString()));
//
//        if (!isTokenValid) {
//            throw new InvalidTokenException("Invalid token");
//        }
        findRegisteredChild(generateReferenceId());

        childService.registerChild(childRequest);
        RegisterChildResponse response = new RegisterChildResponse();
        response.setMessage("Child Registered Successfully");
        response.setDateRegistered(LocalDateTime.now());
        response.setId(generateReferenceId());

        return response;
    }
//    private boolean isLoggedIn() {
//        HospitalAdmin hospitalAdmin = new HospitalAdmin();
//        String token = generateToken(hospitalAdmin.getEmail());
//
//        if (token == null || token.isEmpty()) {
//            return false;
//        }
//        return isTokenValid(token);
//    }
//
//    private boolean isTokenValid(String token) {
//        try {
//            Jwts.parser().
//                    setSigningKey(jwtConfig.getJwtSecretKey()).parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    @Override
    public RegisterDeathResponse registerBody(DeathReq deathReq) {
        deathService.registerDeath(deathReq);
        RegisterDeathResponse response = new RegisterDeathResponse();
        response.setMessage("Registered Successfully ");
        response.setDateRegistered(LocalDateTime.now());
        response.setId(generateReferenceId());
        return response;
    }


    private void findRegisteredChild(String referenceId) {
        Optional<Child>child = childRepository.findChildByReferenceId(referenceId);
        if(child.isPresent()) throw new ChildExist("Child Already Exist");
    }

    @Override
    public String deleteChildInfo(DeleteChildReq deleteChildReq) {
        childService.deleteProfile(deleteChildReq);
        return null;
    }

    @Override
    public Child searchChild(SearchChildReq searchChildReq) {
        Optional<Child> child = childService.findChild(searchChildReq.getName());
        if (child.isEmpty()) throw new ChildNotFound("Child not found");
        return child.get();
    }

    @Override
    public Child updateChildInfo(UpdateChildReq updateChildReq) {
        Child child = findChild(updateChildReq.getName());

        child.setName(updateChildReq.getName());
        child.setAge(updateChildReq.getAge());
        childRepository.save(child);

        return child;
    }

    @Override
    public List<Child> searchChild(SearchChildReq searchChildReq) {
        return childRepository.findChildByNameIsContaining(searchChildReq.getName());
    }

    @Override
    public List<Death> hosSearchDeceased(SearchDeathRequest searchDeathRequest) {
        return hospitalDeathRepo.findDeathByDeceasedName(searchDeathRequest.getDeceasedName());
    }

    @Override
    public List<Death> getAllDeceasedInfo() {
        return hospitalDeathRepo.findAll();
    }

    @Override
    public List<HospitalAdmin> getChildren() {
        return hospitalAdminRepo.findAll();
    }


    private Child findChild(String id) {
        Optional<Child> childOptional = childRepository.findChildByReferenceId(id);
        if (childOptional.isEmpty()) {
            throw new ChildNotFound("Child not found");
        }
        return childOptional.get();
    }

//    private void ensureLoggedIn(String token) {
//
//        if (loggedIn == null || !loggedIn.isLoggedIn()) {
//            throw new NotLoggedInException("User is not logged in");
//        }
//    }


//    @Override
//    public Boolean verifyToken(String token) {
//        Confirmation confirmation = confirmationRepo.findByToken(token);
//        Optional<HospitalAdmin> hospitalAdmin = hospitalAdminRepo
//                .findHospitalAdminByEmailIgnoreCase(confirmation.getHospitalAdmin().getEmail());
//        hospitalAdmin.get().setEnabled(true);
//        hospitalAdminRepo.save(new HospitalAdmin());
//        return Boolean.TRUE;

//        if (hospitalAdmin.isPresent()) {
//            HospitalAdmin hospitalAdmin = new HospitalAdmin();
//            hospitalAdminRepo.save(hospitalAdmin);
//
//            return Boolean.TRUE;
//        } else {
//            return Boolean.FALSE;
//        }
//    }

//    private void validatePassword(SignInHospitalRequest request, HospitalAdmin admin) {
//        if(!admin.getPassword().equals(request.getPassword()))
//            throw  new IncorrectCredentials("Incorrect Username or password");
//    }

    private Optional<HospitalAdmin> getAdmin(String email) {
        Optional<HospitalAdmin> admin =hospitalAdminRepo.findHospitalAdminByEmailIgnoreCase(email);
        if(admin.isEmpty())
            throw new HospitalNotFound("Hospital Does not exist");
//            return admin.get();
        return admin;
    }

//    private Optional<HospitalAdmin> retrieveAdmin(String email) {
//        Optional<HospitalAdmin> newAdmin = retrieveAdmin(email);
//        return null;
//    }

    private void findHospital(String email) {
        Optional<HospitalAdmin> admin = hospitalAdminRepo.findHospitalAdminByEmailIgnoreCase(email);
        if(admin.isPresent())
//            System.out.println("Hospital Already exist");
            throw new HospitalAlreadyExist("Hospital Already Exist");
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<HospitalAdmin> foundAccount = hospitalAdminRepo.findHospitalAdminByEmailIgnoreCase(email);
        HospitalAdmin account = foundAccount.orElseThrow();
        return new SecureUser(account);
    }
}
