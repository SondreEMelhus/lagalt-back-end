# Models
## Account

- Fjerne firstName, lastName, email, chats, messageBoards, messages, statusUpdateBoards, statusUpdate
- firstName, lastName og email trengs egentlig ikke, alt går vi username som er unikt, har test
- Trenger ikke å lagre chats, messageBoards, messages, statusUpdateBoards og statusUpdate ettersom at disse kan lagres lokalt i project objektet, og kan knyttes mot et username som lagres lokalt i tabellene uten tilknyttning til bruker (Dersom vi skal displaye navn på den som postet) 

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 50, nullable = false, unique = true)
    private String username;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column()
    private boolean visible;
    @Column(length = 200)
    private String description;
    @OneToMany(mappedBy = "account")
    Set<Contributor> contributors;
    @OneToMany(mappedBy = "account")
    Set<Application> applications;
    @OneToMany(mappedBy = "account")
    Set<Chat> chats;
    @OneToMany(mappedBy = "account")
    Set<MessageBoard> messageBoards;
    @OneToMany(mappedBy = "account")
    Set<Message> messages;
    @OneToMany(mappedBy = "account")
    Set<StatusUpdateBoard> statusUpdateBoards;
    @OneToMany(mappedBy = "account")
    Set<StatusUpdate> statusUpdate;
    @ManyToMany
    @JoinTable(
            name = "account_skill",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<Skill> skills;
    // Set<Contribution> contributions;
}
```

## Application

- Gjøre status om til en Enum (Godkjent/Ikke godkjent)

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 200, nullable = false)
    private String motivation;
    // Status is initially set to 'Pending'. An admin can set the applications to either 'Accepted' or 'Denied'
    @Column(columnDefinition = "varchar(20) default 'Pending'")
    private String status;
    @ManyToOne
    @JoinColumn
    private Account account;
    @ManyToOne
    @JoinColumn
    private Project project;
}
```

## Chat

- Fjerne Account og i stedet legge inn String username? (Trenger ingen direkte kobling)

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 300)
    private String text;
    @Column()
    private String timestamp;
    @ManyToOne
    @JoinColumn
    private Account account;
    @ManyToOne
    @JoinColumn
    private Project project;
}

```

## Contributer

- Enig med at role bør gjøres om til en Enum

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Contributor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // bør endres til enum!!!!!!
    @Column(length = 50)
    private String role; // is either 'Owner', 'Admin' or 'Member'
    @ManyToOne
    @JoinColumn
    private Account account;
    @ManyToOne
    @JoinColumn
    private Project project;
}
```

## Industry

- Legge til Skills? (Skills er unike til hver industri?)

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String title;
    @OneToMany(mappedBy = "industry")
    private Set<Project> projects;
    @ManyToMany(mappedBy = "industries")
    private Set<Keyword> keywords;
}

```

## Keyword

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String title;
    @ManyToMany
    @JoinTable(
            name = "keyword_industry",
            joinColumns = {@JoinColumn(name = "keyword_id")},
            inverseJoinColumns = {@JoinColumn(name = "industry_id")}
    )
    private Set<Industry> industries;
    @ManyToMany
    @JoinTable(
            name = "keyword_project",
            joinColumns = {@JoinColumn(name = "keyword_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private Set<Project> projects;
}
```

## Message

- Fjerne Account og i stedet bare legge inn String username? (Trenger ingen direkte kobling)

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column()
    private String text;
    @Column()
    private String timestamp;
    @ManyToOne
    @JoinColumn
    private MessageBoard messageBoard;
    @ManyToOne
    @JoinColumn
    private Account account;
}
```

## MessageBoard

- Fjerne account, kan heller lagre String username dersom vi skal vise navn på poster

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class MessageBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column()
    private String text;
    @Column()
    private String timestamp;
    @ManyToOne
    @JoinColumn
    private Account account;
    @ManyToOne
    @JoinColumn
    private Project project;
    @OneToMany(mappedBy = "messageBoard")
    Set<Message> messages;
}
```

## Project

- Endre status til Enum
- Flytte getAccount og toString til projectService?

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50)
    private String title;
    @Column(length = 500)
    private String description;
    @Column(length = 100)
    private String status;
    @OneToMany(mappedBy = "project")
    Set<Contributor> contributors;
    @OneToMany(mappedBy = "project")
    Set<Application> applications;
    @OneToMany(mappedBy = "project")
    Set<Chat> chats;
    @OneToMany(mappedBy = "project")
    Set<MessageBoard> messageBoards;
    @OneToMany(mappedBy = "project")
    Set<StatusUpdateBoard> statusUpdateBoards;
    @ManyToMany
    @JoinTable(
            name = "skill_project",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<Skill> skills;
    @ManyToOne
    @JoinColumn
    private Industry industry;
    @ManyToMany(mappedBy = "projects")
    private Set<Keyword> keywords;

    public HashSet<Account> getAccounts() {
        HashSet<Account> accounts = new HashSet<>();
        for (Contributor c: contributors) {
            accounts.add(c.getAccount());
        }
        return accounts;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + title + '\'' +
                '}';
    }
}
```

## Skill

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String title;
    @ManyToMany(mappedBy = "skills")
    private Set<Account> accounts;
    @ManyToMany(mappedBy = "skills")
    private Set<Project> projects;
}
```

## StatusUpdate

- Fjerne Account og heller knytte det mot en String username dersom vi skal vise navn på poster?

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class StatusUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column()
    private String text;
    @Column()
    private String timestamp;
    @ManyToOne
    @JoinColumn
    private StatusUpdateBoard statusUpdateBoard;
    @ManyToOne
    @JoinColumn
    private Account account;
}
```

## StatusUpdateBoard

- Fjerne Account og heller knytte det mot en String username dersom vi skal vise navn på poster

``` Java
package com.lagaltBE.lagaltBE.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class StatusUpdateBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column()
    private String text;
    @Column()
    private String timestamp;
    @ManyToOne
    @JoinColumn
    private Account account;
    @ManyToOne
    @JoinColumn
    private Project project;
    @OneToMany(mappedBy = "statusUpdateBoard")
    Set<StatusUpdate> statusUpdates;
}

```
# DTOer

## AccountApplication

- Hva er String project?

``` Java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class AccountApplicationDTO {
    private String project;
}
```

## AccountDTO

- Fjerne firstName, lastName, email?
- Legge til keywords

``` Java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class AccountDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String description;
    private boolean visible;
    //private Set<String> contributors;
    private Set<String> skills;
}
```

## ChatDTO

- Bytte ut firstName og lastName med string username?

``` Java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class ChatDTO {
    private int id;
    private String text;
    private String timestamp;
    private String firstName;
    private String lastName;
}
```

## ContributerDTO

- String role bør byttes ut med enum
- String account?

``` Java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class ContributorDTO {
    private int id;
    private String role;
    private String account;
}
```

## IndustryDTO

- Legge til skills?

``` Java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class IndustryDTO {
    private int id;
    private String title;
    private Set<Integer> projects;
    private Set<String> keywords;
}
```

## KeywordDTO

- Trenger vi industries og projects?

``` Java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class KeywordDTO {
    private int id;
    private String title;
    private Set<String> industries;
    private Set<String> projects;
}
```


## MessageboardDTO

- Bytte ut firstName og lastName med username?

``` Java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class MessageBoardDTO {
    private int id;
    private String title;
    private String text;
    private String timestamp;
    private String firstName;
    private String lastName;
}
```
## MessageDTO

- Bytte ut firstName og lastName med username?

``` Java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class MessageDTO {
    private int id;
    private String text;
    private String timestamp;
    private String firstName;
    private String lastName;
}
```

## ProjectApplication

- Bytte ut firstName og lastName med username? (Evt account?)
- Legge til status på application? (Enum godkjet/ikke godkjent)

``` Java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class ProjectApplicationDTO {
    private int id;
    private String motivation;
    private String firstName;
    private String lastName;
}
```

## ProjectDTO

- Vil owner ligge som en contributer?

``` Java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class ProjectDTO {
    private int id;
    private String title;
    private String description;
    private Set<String> contributors;
    private Set<String> skills;
    private String industry;
    private Set<String> keywords;
}
```

## SkillDTO

- Trenger vi å hente ut alle accounts med denne skillen?

``` Java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;
import java.util.Set;

@Data
public class SkillDTO {
    private int id;
    private String title;
    private Set<Integer> accounts;
}
```

## StatusUpdateBoardDTO

- Bytte ut firstName og lastName med username hvis vi skal vise poster?

``` java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class StatusUpdateBoardDTO {
    private int id;
    private String title;
    private String text;
    private String timestamp;
    private String firstName;
    private String lastName;
}

```

- Bytte ut firstName og lastName med username hvis vi skal vise poster?
## StatusUpdateDTO

``` java
package com.lagaltBE.lagaltBE.models.dtos;

import lombok.Data;

@Data
public class StatusUpdateDTO {
    private int id;
    private String text;
    private String timestamp;
    private String firstName;
    private String lastName;
}
```

# Repositories

## AccountRepository

- Hva gjør @Qurey? Kan den flyttes til AccountServiceImpl?

``` java
package com.lagaltBE.lagaltBE.repositories;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    @Query("select a from Account a where a.username = ?1")
    Account findByName(String name);
}

```

## ProjectRepository

- Hva gjør @Qurey? Kan den flyttes til ProjectRepositoryImpl?

``` java
package com.lagaltBE.lagaltBE.repositories;

import com.lagaltBE.lagaltBE.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    @Query("select p from Project p where p.title like %?1%")
    Set<Project> findAllByName(String name);
}
```

# Services

- Tenke gjennom om vi trenger alle CRUD metoder?
- Skal vi update?
- Skal vi delete / deleteById?
- Generelle sjekker om noe eksisterer fra før av

## AccountServiceImp

- findById (try-catch / if else) sjekk?
- Trenger vi findAll?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om bruker eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om bruker eksisterer?
- delete (try-catch / if else) sjekk om bruker eksisterer?
- findByUsername findByName?

``` java
package com.lagaltBE.lagaltBE.services.account;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    @Override
    public Account findById(Integer id) {
        // TODO add or else throw UserAccountNotFoundException like this: or find another solution
        // return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return accountRepository.findById(id).get();
    }

    @Override
    public Collection<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account add(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public Account update(Account entity) {
        return accountRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void delete(Account entity) {
        accountRepository.delete(entity);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByName(username);
    }

}
```

## ApplicationServiceImp
- findById (try-catch / if else) sjekk?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om den eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om den eksisterer?
- delete (try-catch / if else) sjekk om den eksisterer?

``` java
package com.lagaltBE.lagaltBE.services.application;

import com.lagaltBE.lagaltBE.models.Application;
import com.lagaltBE.lagaltBE.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ApplicationServiceImp implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImp(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application findById(Integer id) {
        return applicationRepository.findById(id).get();
    }

    @Override
    public Collection<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    public Application add(Application entity) {
        return applicationRepository.save(entity);
    }

    @Override
    public Application update(Application entity) {
        return applicationRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public void delete(Application entity) {
        applicationRepository.delete(entity);
    }
}
```

## ChatImp

- findById (try-catch / if else) sjekk?
- Trenger vi findAll()?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om den eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om den eksisterer?
- delete (try-catch / if else) sjekk om den eksisterer?

``` java
package com.lagaltBE.lagaltBE.services.chat;

import com.lagaltBE.lagaltBE.models.Chat;
import com.lagaltBE.lagaltBE.repositories.ChatRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ChatServiceImp implements ChatService {

    private final ChatRepository chatRepository;

    public ChatServiceImp(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat findById(Integer id) {
        return chatRepository.findById(id).get();
    }

    @Override
    public Collection<Chat> findAll() {
        return chatRepository.findAll();
    }

    @Override
    public Chat add(Chat entity) {
        return chatRepository.save(entity);
    }

    @Override
    public Chat update(Chat entity) {
        return chatRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        chatRepository.deleteById(id);
    }

    @Override
    public void delete(Chat entity) {
        chatRepository.delete(entity);
    }
}
```

## ContributerServiceImp

- findById (try-catch / if else) sjekk?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om den eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om den eksisterer?
- delete (try-catch / if else) sjekk om den eksisterer?

``` java
package com.lagaltBE.lagaltBE.services.contributor;

import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.repositories.ContributorRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ContributorServiceImp implements ContributorService {

    private final ContributorRepository contributorRepository;

    public ContributorServiceImp(ContributorRepository contributorRepository) {
        this.contributorRepository = contributorRepository;
    }

    @Override
    public Contributor findById(Integer integer) {
        return contributorRepository.findById(integer).get();
    }

    @Override
    public Collection<Contributor> findAll() {
        return contributorRepository.findAll();
    }

    @Override
    public Contributor add(Contributor entity) {
        return contributorRepository.save(entity);
    }

    @Override
    public Contributor update(Contributor entity) {
        return contributorRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        contributorRepository.deleteById(integer);
    }

    @Override
    public void delete(Contributor entity) {
        contributorRepository.delete(entity);
    }
}

```

## IndustryServiceImp

- findById (try-catch / if else) sjekk?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om den eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om den eksisterer?
- delete (try-catch / if else) sjekk om den eksisterer?

``` java
package com.lagaltBE.lagaltBE.services.industry;

import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.repositories.IndustryRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class IndustryServiceImp implements IndustryService {
    private final IndustryRepository industryRepository;

    public IndustryServiceImp(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    @Override
    public Industry findById(Integer id) {
        return industryRepository.findById(id).get();
    }

    @Override
    public Collection<Industry> findAll() {
        return industryRepository.findAll();
    }

    @Override
    public Industry add(Industry entity) {
        return industryRepository.save(entity);
    }

    @Override
    public Industry update(Industry entity) {
        return industryRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        industryRepository.deleteById(id);
    }

    @Override
    public void delete(Industry entity) {
        industryRepository.delete(entity);
    }
}
```

- findById (try-catch / if else) sjekk?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om den eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om den eksisterer?
- delete (try-catch / if else) sjekk om den eksisterer?

## KeywordServiceImp

``` java
package com.lagaltBE.lagaltBE.services.keyword;

import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.repositories.KeywordRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class KeywordServiceImp implements KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordServiceImp(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    @Override
    public Keyword findById(Integer id) {
        return keywordRepository.findById(id).get();
    }

    @Override
    public Collection<Keyword> findAll() {
        return keywordRepository.findAll();
    }

    @Override
    public Keyword add(Keyword entity) {
        return keywordRepository.save(entity);
    }

    @Override
    public Keyword update(Keyword entity) {
        return keywordRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        keywordRepository.deleteById(id);
    }

    @Override
    public void delete(Keyword entity) {
        keywordRepository.delete(entity);
    }
}
```

## MessageServiceImp

- Trenger vi update?
- findById (try-catch / if else) sjekk?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om den eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om den eksisterer?
- delete (try-catch / if else) sjekk om den eksisterer?

``` java
package com.lagaltBE.lagaltBE.services.message;

import com.lagaltBE.lagaltBE.models.Message;
import com.lagaltBE.lagaltBE.models.MessageBoard;
import com.lagaltBE.lagaltBE.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessageServiceImp implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImp(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message findById(Integer id) {
        return messageRepository.findById(id).get();
    }

    @Override
    public Collection<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message add(Message entity) {
        return messageRepository.save(entity);
    }

    @Override
    public Message update(Message entity) {
        return messageRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void delete(Message entity) {
        messageRepository.delete(entity);
    }
}
```

## MessageBoardServiceImp

- Trenger vi findAll?
- findById (try-catch / if else) sjekk?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om den eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om den eksisterer?
- delete (try-catch / if else) sjekk om den eksisterer?

``` java
package com.lagaltBE.lagaltBE.services.messageBoard;

import com.lagaltBE.lagaltBE.models.MessageBoard;
import com.lagaltBE.lagaltBE.repositories.MessageBoardRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessageBoardServiceImp implements MessageBoardService {

    private final MessageBoardRepository messageBoardRepository;

    public MessageBoardServiceImp(MessageBoardRepository messageBoardRepository) {
        this.messageBoardRepository = messageBoardRepository;
    }

    @Override
    public MessageBoard findById(Integer id) {
        return messageBoardRepository.findById(id).get();
    }

    @Override
    public Collection<MessageBoard> findAll() {
        return messageBoardRepository.findAll();
    }

    @Override
    public MessageBoard add(MessageBoard entity) {
        return messageBoardRepository.save(entity);
    }

    @Override
    public MessageBoard update(MessageBoard entity) {
        return messageBoardRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        messageBoardRepository.deleteById(id);
    }

    @Override
    public void delete(MessageBoard entity) {
        messageBoardRepository.delete(entity);
    }
}
```

## ProjectServiceImp

- Trenger vi delete / deleteById?
- findById (try-catch / if else) sjekk?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om den eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om den eksisterer?
- delete (try-catch / if else) sjekk om den eksisterer?

``` java
package com.lagaltBE.lagaltBE.services.project;

import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepostiory;

    public ProjectServiceImp(ProjectRepository projectRepostiory) {
        this.projectRepostiory = projectRepostiory;
    }

    @Override
    public Project findById(Integer integer) {
        return projectRepostiory.findById(integer).get();
    }

    @Override
    public Collection<Project> findAll() {
        return projectRepostiory.findAll();
    }

    @Override
    public Project add(Project entity) {
        return projectRepostiory.save(entity);
    }

    @Override
    public Project update(Project entity) {
        return projectRepostiory.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        projectRepostiory.deleteById(integer);
    }

    @Override
    public void delete(Project entity) {
        projectRepostiory.delete(entity);
    }

    @Override
    public Collection<Project> findAllByName(String name) {
        return projectRepostiory.findAllByName(name);
    }

}
```

## SkillServiceImp

- Trenger vi delete / deleteById?
- findById (try-catch / if else) sjekk?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om den eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om den eksisterer?
- delete (try-catch / if else) sjekk om den eksisterer?

``` java
package com.lagaltBE.lagaltBE.services.skill;

import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.repositories.SkillRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class SkillServiceImp implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImp(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill findById(Integer id) {
        // TODO add or else throw UserAccountNotFoundException like this: or find another solution
        // return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return skillRepository.findById(id).get();
    }

    @Override
    public Collection<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill add(Skill entity) {
        return skillRepository.save(entity);
    }

    @Override
    public Skill update(Skill entity) {
        return skillRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        skillRepository.deleteById(id);
    }

    @Override
    public void delete(Skill entity) {
        skillRepository.delete(entity);
    }
}
```

## StatusUpdateServiceImp

- Trenger vi delete / deleteById
- findById (try-catch / if else) sjekk?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om den eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om den eksisterer?
- delete (try-catch / if else) sjekk om den eksisterer?

``` java
package com.lagaltBE.lagaltBE.services.statusUpdate;

import com.lagaltBE.lagaltBE.models.StatusUpdate;
import com.lagaltBE.lagaltBE.repositories.StatusUpdateRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StatusUpdateServiceImp implements StatusUpdateService{

    private final StatusUpdateRepository statusUpdateRepository;

    public StatusUpdateServiceImp(StatusUpdateRepository statusUpdateRepository) {
        this.statusUpdateRepository = statusUpdateRepository;
    }

    @Override
    public StatusUpdate findById(Integer id) {
        return statusUpdateRepository.findById(id).get();
    }

    @Override
    public Collection<StatusUpdate> findAll() {
        return statusUpdateRepository.findAll();
    }

    @Override
    public StatusUpdate add(StatusUpdate entity) {
        return statusUpdateRepository.save(entity);
    }

    @Override
    public StatusUpdate update(StatusUpdate entity) {
        return statusUpdateRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        statusUpdateRepository.deleteById(id);
    }

    @Override
    public void delete(StatusUpdate entity) {
        statusUpdateRepository.delete(entity);
    }
}
```

## StatusUpdateBoardImp

- Trenger vi delete / deleteById?
- Trenger vi findAll?
- findById (try-catch / if else) sjekk?
- add (try-catch / if else) sjekk om den eksistrer fra før, evt sjekk i front-end?
- update (try-catch / if else) sjekk om den eksisterer, evt sjekk i front-end?
- deleteById (try-catch / if else) sjekk om den eksisterer?
- delete (try-catch / if else) sjekk om den eksisterer?

``` java
package com.lagaltBE.lagaltBE.services.statusUpdateBoard;

import com.lagaltBE.lagaltBE.models.StatusUpdateBoard;
import com.lagaltBE.lagaltBE.repositories.StatusUpdateBoardRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StatusUpdateBoardServiceImp implements StatusUpdateBoardService {

    private final StatusUpdateBoardRepository statusUpdateBoardRepository;

    public StatusUpdateBoardServiceImp(StatusUpdateBoardRepository statusUpdateBoardRepository) {
        this.statusUpdateBoardRepository = statusUpdateBoardRepository;
    }

    @Override
    public StatusUpdateBoard findById(Integer id) {
        return statusUpdateBoardRepository.findById(id).get();
    }

    @Override
    public Collection<StatusUpdateBoard> findAll() {
        return statusUpdateBoardRepository.findAll();
    }

    @Override
    public StatusUpdateBoard add(StatusUpdateBoard entity) {
        return statusUpdateBoardRepository.save(entity);
    }

    @Override
    public StatusUpdateBoard update(StatusUpdateBoard entity) {
        return statusUpdateBoardRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        statusUpdateBoardRepository.deleteById(id);
    }

    @Override
    public void delete(StatusUpdateBoard entity) {
        statusUpdateBoardRepository.delete(entity);
    }
}
```

# Mappers

## AccountMapper

``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.services.contributor.ContributorService;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    @Autowired
    protected ContributorService contributorService;
    @Autowired
    protected SkillService skillService;

    //@Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsToIds")
    //@Mapping(target = "skills", source = "skills", qualifiedByName = "skillsToIds")
    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillsToString")
    public abstract AccountDTO accountToAccountDto(Account account);

    public abstract Collection<AccountDTO> accountToAccountDto(Collection<Account> accounts);

    //@Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsIdsToContributors")
    //@Mapping(target = "skills", source = "skills", qualifiedByName = "skillIdsToSkills")
    //public abstract Account accountDtoToAccount(AccountDTO accountDTO);

    @Named("skillsToString")
    Set<String> mapSkillsToString(Set<Skill> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }

}
/*
    @Named("contributorsToIds")
    Set<Integer> mapContributorsToIds(Set<Contributor> contributors) {
        if (contributors == null) return null;
        return contributors.stream().map(contributor -> contributor.getId()).collect(Collectors.toSet());
    }

     @Named("skillsToIds")
    Set<Integer> mapSkillsToIds(Set<Skill> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }

        @Named("contributorsIdsToContributors")
    Set<Contributor> mapContributorsIdsToContributors(Set<Integer> contributorsIds) {
        if (contributorsIds == null) return null;
        return contributorsIds.stream().map(id -> contributorService.findById(id)).collect(Collectors.toSet());
    }

    @Named("skillIdsToSkills")
    Set<Skill> mapIdsToSkills(Set<Integer> id) {
        return id.stream()
                .map( i -> skillService.findById(i))
                .collect(Collectors.toSet());
    }

*/
```

## ApplicationMapper

- applicationToProjectApplicationDto: Endre mapping til å bruke username?

``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Application;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.dtos.AccountApplicationDTO;
import com.lagaltBE.lagaltBE.models.dtos.ProjectApplicationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class ApplicationMapper {
    @Mapping(target = "firstName", source = "account", qualifiedByName = "applicationToFirstName")
    @Mapping(target = "lastName", source = "account", qualifiedByName = "applicationToLastName")
    public abstract ProjectApplicationDTO applicationToProjectApplicationDto(Application application);

    @Mapping(target = "project", source = "project", qualifiedByName = "applicationToProject")
    public abstract AccountApplicationDTO applicationToAccountApplicationDto(Application application);

    @Named("applicationToFirstName")
    String mapApplicationsToFirstName(Account source) {
        return source.getFirstName();
    }

    @Named("applicationToLastName")
    String mapApplicationsToLastName(Account source) {
        return source.getLastName();
    }

    @Named("applicationToProject")
    String mapApplicationsToProject(Project source) {
        return source.getTitle();
    }
}
```

## ChatMapper

- Trenger vi å knytte en bruker mot en melding?

``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Chat;
import com.lagaltBE.lagaltBE.models.dtos.ChatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
@Mapper(componentModel = "spring")
public abstract class ChatMapper {

    @Mapping(target = "firstName", source = "account", qualifiedByName = "accountToFirstName")
    @Mapping(target = "lastName", source = "account", qualifiedByName = "accountToLastName")
    public abstract ChatDTO chatToChatDto(Chat chat);

    public abstract Collection<ChatDTO> chatToChatDto(Collection<Chat> chat);

    @Named("accountToFirstName")
    String mapAccountToFirstName(Account source) {
        return source.getFirstName();
    }

    @Named("accountToLastName")
    String mapAccountToLastName(Account source) {
        return source.getLastName();
    }
}
```

## ContributerMapper

``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Contributor;
import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.dtos.ContributorDTO;
import com.lagaltBE.lagaltBE.models.dtos.IndustryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ContributorMapper {

    @Mapping(target = "account", source = "account", qualifiedByName = "accountToString")
    public abstract ContributorDTO contributorToContributorDto(Contributor contributor);

    public abstract Collection<ContributorDTO> contributorToContributorDto(Collection<Contributor> contributors);

    @Named("accountToString")
    String mapAccountToString(Account source) {
        return source.getUsername();
    }
}
```

## IndustryMapper

- Skal vi mappe skills mot en industri?

``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.dtos.IndustryDTO;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class IndustryMapper {

    @Autowired
    protected ProjectService projectService;

    @Mapping(target = "projects", source = "projects", qualifiedByName = "projectsToIds")
    @Mapping(target = "keywords", source = "keywords", qualifiedByName = "keywordsToString")
    public abstract IndustryDTO industryToIndustryDto(Industry industry);

    public abstract Collection<IndustryDTO> industryToIndustryDto(Collection<Industry> industries);
/*
    @Mapping(target = "projects", source = "projects", qualifiedByName = "projectsIdsToProjects")
    public abstract Industry industryDtoToIndustry(IndustryDTO dto);
*/
    @Named("projectsToIds")
    Set<Integer> mapProjectsToIds(Set<Project> projects) {
        if (projects == null) return null;
        return projects.stream().map(project -> project.getId()).collect(Collectors.toSet());
    }

    @Named("projectsIdsToProjects")
    Set<Project> mapProjectsIdsToProjects(Set<Integer> projectsIds) {
        if (projectsIds == null) return null;
        return projectsIds.stream().map(id -> projectService.findById(id)).collect(Collectors.toSet());
    }

    @Named("keywordsToString")
    Set<String> mapKeywordsToString(Set<Keyword> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }
}
```

## KeywordMapper

- Noe vi kan endre på keywordToKeywordDto? Fører til at vi får/trenger all info om indstri / prosjekt i get/post

``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.dtos.KeywordDTO;
import com.lagaltBE.lagaltBE.services.industry.IndustryService;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class KeywordMapper {
    @Autowired
    protected IndustryService industryService;
    @Autowired
    protected ProjectService projectService;

    @Mapping(target = "industries", source = "industries", qualifiedByName = "industriesToString")
    @Mapping(target = "projects", source = "projects", qualifiedByName = "projectsToString")
    public abstract KeywordDTO keywordToKeywordDto(Keyword keyword);

    public abstract Collection<KeywordDTO> keywordToKeywordDto(Collection<Keyword> keywords);

    @Named("industriesToString")
    Set<String> mapIndustriesToString(Set<Industry> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }

    @Named("projectsToString")
    Set<String> mapProjectsToString(Set<Project> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }
}
```

## MessageBoardMapper

- Endre MessageBoard til å mappes mot et prosjekt?

``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.MessageBoard;
import com.lagaltBE.lagaltBE.models.dtos.MessageBoardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class MessageBoardMapper {
    @Mapping(target = "firstName", source = "account", qualifiedByName = "accountToFirstName")
    @Mapping(target = "lastName", source = "account", qualifiedByName = "accountToLastName")
    public abstract MessageBoardDTO messageBoardToMessageBoardDto(MessageBoard messageBoard);

    public abstract Collection<MessageBoardDTO> messageBoardToMessageBoardDto(Collection<MessageBoard> messageBoard);

    @Named("accountToFirstName")
    String mapAccountToFirstName(Account source) {
        return source.getFirstName();
    }

    @Named("accountToLastName")
    String mapAccountToLastName(Account source) {
        return source.getLastName();
    }
}
```

## MessageMapper

- Kan en message mappes mot et messageBoard / chat? (Brukes denne for chat eller messageBoard)


``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Message;
import com.lagaltBE.lagaltBE.models.dtos.MessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {

    @Mapping(target = "firstName", source = "account", qualifiedByName = "accountToFirstName")
    @Mapping(target = "lastName", source = "account", qualifiedByName = "accountToLastName")
    public abstract MessageDTO messageToMessageDto(Message message);

    public abstract Collection<MessageDTO> messageToMessageDto(Collection<Message> message);

    @Named("accountToFirstName")
    String mapAccountToFirstName(Account source) {
        return source.getFirstName();
    }

    @Named("accountToLastName")
    String mapAccountToLastName(Account source) {
        return source.getLastName();
    }
}
```

## ProjectMapper

``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.*;
import com.lagaltBE.lagaltBE.models.dtos.ProjectDTO;
import com.lagaltBE.lagaltBE.services.contributor.ContributorService;
import com.lagaltBE.lagaltBE.services.industry.IndustryService;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

    @Autowired
    protected ContributorService contributorService;
    @Autowired
    protected SkillService skillService;
    @Autowired
    protected IndustryService industryService;

    @Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsToString")
    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillsToString")
    @Mapping(target = "industry", source = "industry", qualifiedByName = "industriesToString" )
    @Mapping(target = "keywords", source = "keywords", qualifiedByName = "keywordsToString" )
    public abstract ProjectDTO projectToProjectDto(Project project);

    public abstract Collection<ProjectDTO> projectToProjectDto(Collection<Project> project);

    /*@Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsIdsToContributors")
    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillIdsToSkills")
    public abstract Project projectDtoToProject(ProjectDTO projectDTO);*/

    @Named("contributorsToString")
    Set<String> mapContributorsToIds(Set<Contributor> contributors) {
        if (contributors == null) return null;
        return contributors.stream().map(contributor -> contributor.getAccount().getUsername()).collect(Collectors.toSet());
    }

    @Named("skillsToString")
    Set<String> mapSkillsToIds(Set<Skill> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }

    @Named("keywordsToString")
    Set<String> mapKeywordsToString(Set<Keyword> source) {
        if(source == null) return null;
        return source.stream().map(s -> s.getTitle()).collect(Collectors.toSet());
    }

/*
    @Mapping(target = "contributors", source = "contributors", qualifiedByName = "contributorsIdsToContributors")
    @Mapping(target = "skills", source = "skills", qualifiedByName = "skillIdsToSkills")
    @Mapping(target = "industry", source = "industry", qualifiedByName = "industryIdsToIndustry") // try adding industry.id if it does not work, or remove industry.id on projectToProjectDto
    public abstract Project projectDtoToProject(ProjectDTO projectDTO);

    @Named("contributorsToIds")
    Set<Integer> mapContributorsToIds(Set<Contributor> contributors) {
        if (contributors == null) return null;
        return contributors.stream().map(Contributor::getId).collect(Collectors.toSet());
    }

    @Named("contributorsIdsToContributors")
    Set<Contributor> mapContributorsIdsToContributors(Set<Integer> contributorsIds) {
        if (contributorsIds == null) return null;
        return contributorsIds.stream().map(id -> contributorService.findById(id)).collect(Collectors.toSet());
    }
    */
    @Named("industryIdsToIndustry")
    Industry mapIndustryIdsToIndustries(Integer industryId) {
        return industryService.findById(industryId);
    }

    @Named("industriesToString")
    String mapIndustryToIds(Industry industry) {
        return industry.getTitle();
    }
}
    /*
    @Named("skillIdsToSkills")
    Set<Skill> mapIdsToSkills(Set<Integer> id) {
        return id.stream()
                .map( i -> skillService.findById(i))
                .collect(Collectors.toSet());
    }

    @Named("skillsToIds")
    Set<Integer> mapSkillsToIds(Set<Skill> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }
 */
```

## SkillMapper

``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.SkillDTO;
import com.lagaltBE.lagaltBE.services.account.AccountService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class SkillMapper {
    @Autowired
    protected AccountService accountService;

    @Mapping(target = "accounts", source = "accounts", qualifiedByName = "accountsToIds")
    public abstract SkillDTO skillToSkillDto(Skill skill);

    public abstract Collection<SkillDTO> skillToSkillDto(Collection<Skill> skills);

    @Mapping(target = "accounts", source = "accounts", qualifiedByName = "accountsIdsToAccounts")
    public abstract Skill skillDtoToSkill(SkillDTO dto);

    @Named("accountsIdsToAccounts")
    Set<Account> mapIdsToUserAccounts(Set<Integer> id) {
        return id.stream()
                .map( i -> accountService.findById(i))
                .collect(Collectors.toSet());
    }

    @Named("accountsToIds")
    Set<Integer> mapUserAccountsToIds(Set<Account> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }
}
```

## StatusMapper

- Kan denne mappes mot en applicationId?

``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.StatusUpdateBoard;
import com.lagaltBE.lagaltBE.models.dtos.StatusUpdateBoardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class StatusUpdateBoardMapper {
    @Mapping(target = "firstName", source = "account", qualifiedByName = "accountToFirstName")
    @Mapping(target = "lastName", source = "account", qualifiedByName = "accountToLastName")
    public abstract StatusUpdateBoardDTO statusUpdateBoardToStatusUpdateBoardDto(StatusUpdateBoard statusUpdateBoard);

    public abstract Collection<StatusUpdateBoardDTO> statusUpdateBoardToStatusUpdateBoardDto(Collection<StatusUpdateBoard> statusUpdateBoard);

    @Named("accountToFirstName")
    String mapAccountToFirstName(Account source) {
        return source.getFirstName();
    }

    @Named("accountToLastName")
    String mapAccountToLastName(Account source) {
        return source.getLastName();
    }
}
```

## StatusUpdateMapper

- Kan denne mappes til en statusBoardId?


``` java
package com.lagaltBE.lagaltBE.mappers;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.StatusUpdate;
import com.lagaltBE.lagaltBE.models.dtos.StatusUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class StatusUpdateMapper {
    @Mapping(target = "firstName", source = "account", qualifiedByName = "accountToFirstName")
    @Mapping(target = "lastName", source = "account", qualifiedByName = "accountToLastName")
    public abstract StatusUpdateDTO statusUpdateToStatusUpdateDto(StatusUpdate statusUpdate);

    public abstract Collection<StatusUpdateDTO> statusUpdateToStatusUpdateDto(Collection<StatusUpdate> statusUpdate);

    @Named("accountToFirstName")
    String mapAccountToFirstName(Account source) {
        return source.getFirstName();
    }

    @Named("accountToLastName")
    String mapAccountToLastName(Account source) {
        return source.getLastName();
    }
}
```

# Controllers

## AccountController

``` java
package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.AccountMapper;
import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.mappers.SkillMapper;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.IndustryDTO;
import com.lagaltBE.lagaltBE.models.dtos.ProjectDTO;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
import com.lagaltBE.lagaltBE.util.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.lagaltBE.lagaltBE.services.account.AccountService;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final SkillMapper skillMapper;
    private final SkillService skillService;

    public AccountController(AccountService accountService, AccountMapper accountMapper, SkillMapper skillMapper, SkillService skillService) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.skillMapper = skillMapper;
        this.skillService = skillService;
    }

    @Operation(summary = "Get all user accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "Users does not exist with supplied ID",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping //GET: api/v1/accounts
    public ResponseEntity<Collection<AccountDTO>> getAll() {
        Collection<AccountDTO> accounts = accountMapper.accountToAccountDto(
                accountService.findAll()
        );
        return ResponseEntity.ok(accounts);
    }

    @Operation(summary = "Get a user account by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "User does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}") //GET: api/v1/accounts/1
    public ResponseEntity<AccountDTO> getById(@PathVariable int id) {
        AccountDTO account = accountMapper.accountToAccountDto(
                accountService.findById(id)
        );
        return ResponseEntity.ok(account);
    }

    @Operation(summary = "Get an account by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProjectDTO.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "account does not exist with supplied username",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("search") // GET: localhost:8080/api/v1/characters/1
    public ResponseEntity<AccountDTO> findByName(@RequestParam String username){
        AccountDTO dto = accountMapper.accountToAccountDto(
                accountService.findByUsername(username)
        );
        System.out.println(dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Add an account")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping    //POST: api/v1/accounts
    public  ResponseEntity add(@RequestBody Account account) {
        Account user = accountService.add(account);
        URI location = URI.create("accounts/" + user.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a user account")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "User successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "User not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}") //PUT: api/v1/accounts
    public ResponseEntity update(@RequestBody Account account, @PathVariable int id) {
        if (id != account.getId())
            return ResponseEntity.badRequest().build();
        accountService.update(account);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a user account")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @DeleteMapping("{id}")  //DELETE: api/v1/accounts/1
    public ResponseEntity delete(@PathVariable int id) {
        accountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get skills of a user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/{id}/skills")
    public ResponseEntity getUserSkills(@PathVariable int id){
        Account user = accountService.findById(id);
        Set<Skill> skills = user.getSkills();
        return ResponseEntity.ok(skills.stream().map(skillMapper::skillToSkillDto));
    }

    @Operation(summary = "Adds a skill to a user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Skill successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "User not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/{userId}/addSkill")
    public ResponseEntity addSkill(@PathVariable int userId, @RequestBody int skillId) {
        Account user = accountService.findById(userId);
        Skill skill = skillService.findById(skillId);
        Set<Skill> skills = user.getSkills();
        skills.add(skill);
        user.setSkills(skills);
        accountService.update(user);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Removes a skill from a user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Skill successfully removed",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "User not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/{userId}/removeSkill")
    public ResponseEntity removeSkill(@PathVariable int userId, @RequestBody int skillId) {
        Account user = accountService.findById(userId);
        Skill skill = skillService.findById(skillId);
        Set<Skill> skills = user.getSkills();
        skills.remove(skill);
        user.setSkills(skills);
        accountService.update(user);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Set profile to visible")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Profile successfully set to visible",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "User not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/{id}/setProfileToVisible")
    public ResponseEntity setProfileToVisible(@PathVariable int id) {
        Account account = accountService.findById(id);
        account.setVisible(true);
        accountService.update(account);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Set profile to hidden")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Profile successfully set to hidden",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "User not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/{id}/setProfileToHidden")
    public ResponseEntity setProfileToHidden(@PathVariable int id) {
        Account account = accountService.findById(id);
        account.setVisible(false);
        accountService.update(account);
        return ResponseEntity.noContent().build();
    }
}
```

## ApplicationController

``` java
package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.ApplicationMapper;
import com.lagaltBE.lagaltBE.models.*;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.ProjectApplicationDTO;
import com.lagaltBE.lagaltBE.services.account.AccountService;
import com.lagaltBE.lagaltBE.services.application.ApplicationService;
import com.lagaltBE.lagaltBE.services.contributor.ContributorService;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/applications")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;
    private final ProjectService projectService;
    private final AccountService accountService;
    private final ContributorService contributorService;

    public ApplicationController(ApplicationService applicationService, ApplicationMapper applicationMapper, ProjectService projectService, AccountService accountService, ContributorService contributorService) {
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
        this.projectService = projectService;
        this.accountService = accountService;
        this.contributorService = contributorService;
    }

    @Operation(summary = "Get pending applications to a project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Project does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/project/{id}")
    public ResponseEntity<Collection<ProjectApplicationDTO>> getApplicationsToProject(@PathVariable int id) {
        Project project = projectService.findById(id);
        Set<Application> projectApplications = project.getApplications();
        Set<ProjectApplicationDTO> projectApplicationDTO = new HashSet<>();
        for (Application application : projectApplications){
            if (application.getStatus().compareTo("Pending") == 0) {
                projectApplicationDTO.add(applicationMapper.applicationToProjectApplicationDto(application));
            }
        }
        return ResponseEntity.ok(projectApplicationDTO);
    }

    @Operation(summary = "Get all applications from a user to projects by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "User does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/account/{id}")
    public ResponseEntity getApplicationsByAccount(@PathVariable int id) {
        Account account = accountService.findById(id);
        Set<Application> applications = account.getApplications();
        return ResponseEntity.ok(applications.stream().map(applicationMapper::applicationToAccountApplicationDto));
    }

    @Operation(summary = "Accept an application")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Application successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Application not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}/accept")
    public ResponseEntity accept(@PathVariable int id) {
        Application application = applicationService.findById(id);
        application.setStatus("Accepted");
        applicationService.update(application);
        // add the user as a contributor to the project
        Contributor contributor = new Contributor();
        contributor.setRole("member");
        contributor.setAccount(application.getAccount());
        contributor.setProject(application.getProject());
        contributorService.add(contributor);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deny an application")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Application successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Application not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}/deny")
    public ResponseEntity deny(@PathVariable int id) {
        Application application = applicationService.findById(id);
        application.setStatus("Denied");
        applicationService.update(application);
        return ResponseEntity.noContent().build();
    }
}
```

## ChatController

``` java
package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.ChatMapper;
import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Chat;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.ChatDTO;
import com.lagaltBE.lagaltBE.services.chat.ChatService;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/chats")
public class ChatController {

    private final ChatMapper chatMapper;
    private final ProjectService projectService;
    private final ChatService chatService;

    public ChatController(ChatMapper chatMapper, ProjectService projectService, ChatService chatService) {
        this.chatMapper = chatMapper;
        this.projectService = projectService;
        this.chatService = chatService;
    }

    @Operation(summary = "Get all chats of a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/project/{id}")
    public ResponseEntity getAllChatsOfProject(@PathVariable int id){
        Project project = projectService.findById(id);
        Set<Chat> chats = project.getChats();
        return ResponseEntity.ok(chats.stream().map(chatMapper::chatToChatDto));
    }

    @Operation(summary = "Get a chat by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Chat does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<ChatDTO> getById(@PathVariable int id) {
        ChatDTO chat = chatMapper.chatToChatDto(
                chatService.findById(id)
        );
        return ResponseEntity.ok(chat);
    }

    @Operation(summary = "Add a chat to a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping
    public  ResponseEntity add(@RequestBody Chat chat) {
        Chat newChat = chatService.add(chat);
        URI location = URI.create("chats/" + newChat.getId());
        return ResponseEntity.created(location).build();
    }
}
```

## IndustryController

``` java
package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.IndustryMapper;
import com.lagaltBE.lagaltBE.mappers.KeywordMapper;
import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.IndustryDTO;
import com.lagaltBE.lagaltBE.services.industry.IndustryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/industries")
public class IndustryController {
    private final IndustryService industryService;
    private final IndustryMapper industryMapper;
    private final KeywordMapper keywordMapper;

    public IndustryController(IndustryService industryService, IndustryMapper industryMapper, KeywordMapper keywordMapper) {
        this.industryService = industryService;
        this.industryMapper = industryMapper;
        this.keywordMapper = keywordMapper;
    }

    @Operation(summary = "Get all industries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping
    public ResponseEntity<Collection<IndustryDTO>> getAll() {
        Collection<IndustryDTO> industry = industryMapper.industryToIndustryDto(
                industryService.findAll()
        );
        return ResponseEntity.ok(industry);
    }

    @Operation(summary = "Get a industry by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Industry does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<IndustryDTO> getById(@PathVariable int id) {
        IndustryDTO industryDTO = industryMapper.industryToIndustryDto(
                industryService.findById(id)
        );
        return ResponseEntity.ok(industryDTO);
    }

    @Operation(summary = "Add a industry")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping
    public  ResponseEntity add(@RequestBody Industry industry) {
        Industry newIndustry = industryService.add(industry);
        URI location = URI.create("industries/" + newIndustry.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a industry")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Industry successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Industry not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Industry industry, @PathVariable int id) {
        if (id != industry.getId())
            return ResponseEntity.badRequest().build();
        industryService.update(industry);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a industry")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such industry",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        industryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get keywords of a industry")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such industry",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/{id}/keywords")
    public ResponseEntity getIndustryKeywords(@PathVariable int id){
        Industry industry = industryService.findById(id);
        return ResponseEntity.ok(keywordMapper.keywordToKeywordDto(industry.getKeywords()));
    }
}
```

## KeywordController

``` java
package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.KeywordMapper;
import com.lagaltBE.lagaltBE.models.Industry;
import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.IndustryDTO;
import com.lagaltBE.lagaltBE.models.dtos.KeywordDTO;
import com.lagaltBE.lagaltBE.services.keyword.KeywordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/keywords")
public class KeywordController {
    private final KeywordService keywordService;
    private final KeywordMapper keywordMapper;

    public KeywordController(KeywordService keywordService, KeywordMapper keywordMapper) {
        this.keywordService = keywordService;
        this.keywordMapper = keywordMapper;
    }

    @Operation(summary = "Get all keywords")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping
    public ResponseEntity<Collection<KeywordDTO>> getAll() {
        Collection<KeywordDTO> keyword = keywordMapper.keywordToKeywordDto(
                keywordService.findAll()
        );
        return ResponseEntity.ok(keyword);
    }

    @Operation(summary = "Get a keyword by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Keyword does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<KeywordDTO> getById(@PathVariable int id) {
        KeywordDTO keywordDTO = keywordMapper.keywordToKeywordDto(
                keywordService.findById(id)
        );
        return ResponseEntity.ok(keywordDTO);
    }

    @Operation(summary = "Add a keyword")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping
    public  ResponseEntity add(@RequestBody Keyword keyword) {
        Keyword newKeyword = keywordService.add(keyword);
        URI location = URI.create("keywords/" + newKeyword.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a keyword")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Keyword successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Keyword not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Keyword keyword, @PathVariable int id) {
        if (id != keyword.getId())
            return ResponseEntity.badRequest().build();
        keywordService.update(keyword);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a keyword")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such keyword",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        keywordService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
```

## MessageBoardController

``` java
package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.MessageBoardMapper;
import com.lagaltBE.lagaltBE.models.Keyword;
import com.lagaltBE.lagaltBE.models.MessageBoard;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.KeywordDTO;
import com.lagaltBE.lagaltBE.models.dtos.MessageBoardDTO;
import com.lagaltBE.lagaltBE.services.messageBoard.MessageBoardService;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/messageBoards")
public class MessageBoardController {

    private final MessageBoardService messageBoardService;
    private final MessageBoardMapper messageBoardMapper;
    private final ProjectService projectService;

    public MessageBoardController(MessageBoardService messageBoardService, MessageBoardMapper messageBoardMapper, ProjectService projectService) {
        this.messageBoardService = messageBoardService;
        this.messageBoardMapper = messageBoardMapper;
        this.projectService = projectService;
    }

    @Operation(summary = "Get all message boards of a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping("/project/{id}")
    public ResponseEntity getAllMessageBoardsOfProject(@PathVariable int id) {
        Project project = projectService.findById(id);
        Set<MessageBoard> messageBoards = project.getMessageBoards();
        return ResponseEntity.ok(messageBoards.stream().map(messageBoardMapper::messageBoardToMessageBoardDto));
    }

    @Operation(summary = "Get a message board by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Message board does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<MessageBoardDTO> getById(@PathVariable int id) {
        MessageBoardDTO messageBoardDTO = messageBoardMapper.messageBoardToMessageBoardDto(
                messageBoardService.findById(id)
        );
        return ResponseEntity.ok(messageBoardDTO);
    }

    @Operation(summary = "Updates a message board")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Message board successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Message board not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody MessageBoard messageBoard, @PathVariable int id) {
        if (id != messageBoard.getId())
            return ResponseEntity.badRequest().build();
        messageBoardService.update(messageBoard);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add a message board")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping
    public  ResponseEntity add(@RequestBody MessageBoard messageBoard) {
        MessageBoard newMessageBoard = messageBoardService.add(messageBoard);
        URI location = URI.create("messageBoards/" + newMessageBoard.getId());
        return ResponseEntity.created(location).build();
    }
}
```

## MessageController

``` java
package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.MessageMapper;
import com.lagaltBE.lagaltBE.models.Message;
import com.lagaltBE.lagaltBE.models.MessageBoard;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.MessageDTO;
import com.lagaltBE.lagaltBE.services.message.MessageService;
import com.lagaltBE.lagaltBE.services.messageBoard.MessageBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/messages")
public class MessageController {
    private final MessageMapper messageMapper;
    private final MessageService messageService;
    private final MessageBoardService messageBoardService;

    public MessageController(MessageMapper messageMapper, MessageService messageService, MessageBoardService messageBoardService) {
        this.messageMapper = messageMapper;
        this.messageService = messageService;
        this.messageBoardService = messageBoardService;
    }

    @Operation(summary = "Get a message by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Message does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<MessageDTO> getById(@PathVariable int id) {
        MessageDTO messageDTO = messageMapper.messageToMessageDto(
                messageService.findById(id)
        );
        return ResponseEntity.ok(messageDTO);
    }

    @Operation(summary = "Get all messages in a message board")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping("/messageBoard/{id}")
    public ResponseEntity getAllMessagesInMessageBoard(@PathVariable int id) {
        MessageBoard messageBoard = messageBoardService.findById(id);
        Set<Message> messages = messageBoard.getMessages();
        return ResponseEntity.ok(messages.stream().map(messageMapper::messageToMessageDto));
    }

    @Operation(summary = "Updates a message")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Message successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Message not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Message message, @PathVariable int id) {
        if (id != message.getId())
            return ResponseEntity.badRequest().build();
        messageService.update(message);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add a message")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping
    public  ResponseEntity add(@RequestBody Message message) {
        Message newMessage = messageService.add(message);
        URI location = URI.create("messages/" + newMessage.getId());
        return ResponseEntity.created(location).build();
    }

}
```

## ProjectController

``` java
package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.ProjectMapper;
import com.lagaltBE.lagaltBE.mappers.SkillMapper;
import com.lagaltBE.lagaltBE.mappers.*;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.ProjectDTO;
import com.lagaltBE.lagaltBE.services.contributor.ContributorService;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
import com.lagaltBE.lagaltBE.util.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final SkillMapper skillMapper;
    private final SkillService skillService;
    private final IndustryMapper industryMapper;
    private final KeywordMapper keywordMapper;
    private final ContributorService contributorService;
    private final ContributorMapper contributorMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper, AccountMapper accountMapper, SkillMapper skillMapper, SkillService skillService, IndustryMapper industryMapper, KeywordMapper keywordMapper, ContributorService contributorService, ContributorMapper contributorMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
        this.skillMapper = skillMapper;
        this.skillService = skillService;
        this.industryMapper = industryMapper;
        this.keywordMapper = keywordMapper;
        this.contributorService = contributorService;
        this.contributorMapper = contributorMapper;
    }

    @Operation(summary = "Get all projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProjectDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "Project does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)) })
    })
    @GetMapping //GET: api/v1/projects
    public ResponseEntity getAll() {
        Collection<ProjectDTO> projects = projectMapper.projectToProjectDto(
                projectService.findAll()
        );
        return ResponseEntity.ok(projects);
    }

    @Operation(summary = "Get a project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProjectDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Project does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorResponse.class))})
    })

    @GetMapping("{id}") //GET: api/v1/projects/1
    public ResponseEntity getById(@PathVariable int id) {
        ProjectDTO projectDTO = projectMapper.projectToProjectDto(
                projectService.findById(id)
        );
        return ResponseEntity.ok(projectDTO);
    }

    @Operation(summary = "Get a project by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProjectDTO.class)))}),
            @ApiResponse(responseCode = "404",
                    description = "project does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("search") // GET: localhost:8080/api/v1/characters/1
    public ResponseEntity<Collection<ProjectDTO>> findAllByName(@RequestParam String name){
        Collection<ProjectDTO> dtos = projectMapper.projectToProjectDto(
                projectService.findAllByName(name)
        );
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Adds a new project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "project successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class))})
    })
    @PostMapping //POST: api/v1/projects
    public ResponseEntity add(@RequestBody Project project) {
        Project p = projectService.add(project);
        URI location = URI.create("projects/" + p.getId());
        return ResponseEntity.created(location).build();
    }

    // virker ikke?
    @Operation(summary = "Updates a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Project successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Project not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")  //POST: api/v1/projects/1
    public ResponseEntity update(@RequestBody Project project, @PathVariable int id) {
        if ( id != project.getId() ) {
            return ResponseEntity.badRequest().build();
        }
        projectService.update(project);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @DeleteMapping("{id}")  //POST: api/v1/projects/1
    public ResponseEntity delete(@PathVariable int id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get skills of a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/{id}/skills")
    public ResponseEntity getProjectSkills(@PathVariable int id){
        Project project = projectService.findById(id);
        Set<Skill> skills = project.getSkills();
        return ResponseEntity.ok(skills.stream().map(skillMapper::skillToSkillDto));
    }

    @Operation(summary = "Add a skill to a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Skill successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Project not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/{projectId}/addSkill")
    public ResponseEntity addSkill(@PathVariable int projectId, @RequestBody int skillId) {
        Project project = projectService.findById(projectId);
        Skill skill = skillService.findById(skillId);
        Set<Skill> skills = project.getSkills();
        skills.add(skill);
        project.setSkills(skills);
        projectService.update(project);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Removes a skill from a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Skill successfully removed",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Project not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("/{projectId}/removeSkill")
    public ResponseEntity removeSkill(@PathVariable int projectId, @RequestBody int skillId) {
        Project project = projectService.findById(projectId);
        Skill skill = skillService.findById(skillId);
        Set<Skill> skills = project.getSkills();
        skills.remove(skill);
        project.setSkills(skills);
        projectService.update(project);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get industry of a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/{id}/industry")
    public ResponseEntity getProjectIndustry(@PathVariable int id){
        Project project = projectService.findById(id);
        return ResponseEntity.ok(industryMapper.industryToIndustryDto(project.getIndustry()));
    }

    @Operation(summary = "Get keywords of a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/{id}/keywords")
    public ResponseEntity getProjectKeywords(@PathVariable int id){
        Project project = projectService.findById(id);
        return ResponseEntity.ok(keywordMapper.keywordToKeywordDto(project.getKeywords()));
    }

    @Operation(summary = "Get contributors of a project")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("/{id}/contributors")
    public ResponseEntity getProjectContributors(@PathVariable int id){
        Project project = projectService.findById(id);
        return ResponseEntity.ok(contributorMapper.contributorToContributorDto(project.getContributors()));
    }
}
```

## SkillController

``` java
package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.SkillMapper;
import com.lagaltBE.lagaltBE.models.Skill;
import com.lagaltBE.lagaltBE.models.dtos.SkillDTO;
import com.lagaltBE.lagaltBE.services.skill.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/skills")
public class SkillController {

    private final SkillService skillService;
    private final SkillMapper skillMapper;

    public SkillController(SkillService skillService, SkillMapper skillMapper) {
        this.skillService = skillService;
        this.skillMapper = skillMapper;
    }

    @Operation(summary = "Get all skills")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SkillDTO.class))) }),
            @ApiResponse(responseCode = "404",
                    description = "Skill does not exist with supplied ID",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping
    public ResponseEntity getAll() {
        Collection<SkillDTO> skills = skillMapper.skillToSkillDto(
                skillService.findAll()
        );
        return ResponseEntity.ok(skills);
    }

    @Operation(summary = "Get a skill by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SkillDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Skill does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<Skill> getById(@PathVariable int id) {
        return ResponseEntity.ok(skillService.findById(id));
    }

    @Operation(summary = "Add a skill")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping
    public  ResponseEntity add(@RequestBody Skill skill) {
        Skill newSkill = skillService.add(skill);
        URI location = URI.create("skills/" + newSkill.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Updates a skill")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Skill successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Skill not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Skill skill, @PathVariable int id) {
        if (id != skill.getId())
            return ResponseEntity.badRequest().build();
        skillService.update(skill);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a skill")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "no such skill",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id) {
        skillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
```

## StatusUpdateBoardController

``` java
package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.StatusUpdateBoardMapper;
import com.lagaltBE.lagaltBE.models.Project;
import com.lagaltBE.lagaltBE.models.StatusUpdateBoard;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.StatusUpdateBoardDTO;
import com.lagaltBE.lagaltBE.services.project.ProjectService;
import com.lagaltBE.lagaltBE.services.statusUpdateBoard.StatusUpdateBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/statusUpdateBoards")
public class StatusUpdateBoardController {
    private final StatusUpdateBoardService statusUpdateBoardService;
    private final StatusUpdateBoardMapper statusUpdateBoardMapper;
    private final ProjectService projectService;

    public StatusUpdateBoardController(StatusUpdateBoardService statusUpdateBoardService, StatusUpdateBoardMapper statusUpdateBoardMapper, ProjectService projectService) {
        this.statusUpdateBoardService = statusUpdateBoardService;
        this.statusUpdateBoardMapper = statusUpdateBoardMapper;
        this.projectService = projectService;
    }

    @Operation(summary = "Get all status update boards of a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping("/project/{id}")
    public ResponseEntity getAllStatusUpdateBoardsOfProject(@PathVariable int id) {
        Project project = projectService.findById(id);
        Set<StatusUpdateBoard> statusUpdateBoards = project.getStatusUpdateBoards();
        return ResponseEntity.ok(statusUpdateBoards.stream().map(statusUpdateBoardMapper::statusUpdateBoardToStatusUpdateBoardDto));
    }

    @Operation(summary = "Get a status update board by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Status update board does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<StatusUpdateBoardDTO> getById(@PathVariable int id) {
        StatusUpdateBoardDTO statusUpdateBoardDTO = statusUpdateBoardMapper.statusUpdateBoardToStatusUpdateBoardDto(
                statusUpdateBoardService.findById(id)
        );
        return ResponseEntity.ok(statusUpdateBoardDTO);
    }

    @Operation(summary = "Updates a status update board")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Status update board successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Status update board not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody StatusUpdateBoard statusUpdateBoard, @PathVariable int id) {
        if (id != statusUpdateBoard.getId())
            return ResponseEntity.badRequest().build();
        statusUpdateBoardService.update(statusUpdateBoard);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add a status update board")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping
    public ResponseEntity add(@RequestBody StatusUpdateBoard statusUpdateBoard) {
        StatusUpdateBoard newStatusUpdateBoard = statusUpdateBoardService.add(statusUpdateBoard);
        URI location = URI.create("statusUpdateBoards/" + newStatusUpdateBoard.getId());
        return ResponseEntity.created(location).build();
    }
}
```

## StatusUpdateController

``` java
package com.lagaltBE.lagaltBE.controllers;

import com.lagaltBE.lagaltBE.mappers.StatusUpdateMapper;
import com.lagaltBE.lagaltBE.models.StatusUpdate;
import com.lagaltBE.lagaltBE.models.StatusUpdateBoard;
import com.lagaltBE.lagaltBE.models.dtos.AccountDTO;
import com.lagaltBE.lagaltBE.models.dtos.StatusUpdateDTO;
import com.lagaltBE.lagaltBE.services.statusUpdate.StatusUpdateService;
import com.lagaltBE.lagaltBE.services.statusUpdateBoard.StatusUpdateBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/statusUpdates")
public class StatusUpdateController {
    private final StatusUpdateMapper statusUpdateMapper;
    private final StatusUpdateService statusUpdateService;
    private final StatusUpdateBoardService statusUpdateBoardService;

    public StatusUpdateController(StatusUpdateMapper statusUpdateMapper, StatusUpdateService statusUpdateService, StatusUpdateBoardService statusUpdateBoardService) {
        this.statusUpdateMapper = statusUpdateMapper;
        this.statusUpdateService = statusUpdateService;
        this.statusUpdateBoardService = statusUpdateBoardService;
    }


    @Operation(summary = "Get a status update by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Status update does not exist with supplied ID",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<StatusUpdateDTO> getById(@PathVariable int id) {
        StatusUpdateDTO statusUpdateDTO = statusUpdateMapper.statusUpdateToStatusUpdateDto(
                statusUpdateService.findById(id)
        );
        return ResponseEntity.ok(statusUpdateDTO);
    }

    @Operation(summary = "Get all status updates in a status update board by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AccountDTO.class))) })
    })
    @GetMapping("/statusUpdateBoard/{id}")
    public ResponseEntity getAllStatusUpdatesInStatusUpdateBoard(@PathVariable int id) {
        StatusUpdateBoard statusUpdateBoard = statusUpdateBoardService.findById(id);
        Set<StatusUpdate> statusUpdates = statusUpdateBoard.getStatusUpdates();
        return ResponseEntity.ok(statusUpdates.stream().map(statusUpdateMapper::statusUpdateToStatusUpdateDto));
    }

    @Operation(summary = "Updates a status update")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Status update successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Status update not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody StatusUpdate statusUpdate, @PathVariable int id) {
        if (id != statusUpdate.getId())
            return ResponseEntity.badRequest().build();
        statusUpdateService.update(statusUpdate);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Add a status update")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201",
                    description = "success",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) })
    })
    @PostMapping
    public  ResponseEntity add(@RequestBody StatusUpdate statusUpdate) {
        StatusUpdate newStatusUpdate = statusUpdateService.add(statusUpdate);
        URI location = URI.create("statusUpdates/" + newStatusUpdate.getId());
        return ResponseEntity.created(location).build();
    }
}
```
