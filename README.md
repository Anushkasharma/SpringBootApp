```
   ____    ,---.   .--.  ___    _    .-'''-. .---.  .---. .--.   .--.     ____
 .'  __ `. |    \  |  |.'   |  | |  / _     \|   |  |_ _| |  | _/  /    .'  __ `.
/   '  \  \|  ,  \ |  ||   .'  | | (`' )/`--'|   |  ( ' ) | (`' ) /    /   '  \  \
|___|  /  ||  |\_ \|  |.'  '_  | |(_ o _).   |   '-(_{;}_)|(_ ()_)     |___|  /  |
   _.-`   ||  _( )_\  |'   ( \.-.| (_,_). '. |      (_,_) | (_,_)   __    _.-`   |
.'   _    || (_ o _)  |' (`. _` /|.---.  \  :| _ _--.   | |  |\ \  |  |.'   _    |
|  _( )_  ||  (_,_)\  || (_ (_) _)\    `-'  ||( ' ) |   | |  | \ `'   /|  _( )_  |
\ (_ o _) /|  |    |  | \ /  . \ / \       / (_{;}_)|   | |  |  \    / \ (_ o _) /
 '.(_,_).' '--'    '--'  ``-'`-''   `-...-'  '(_,_) '---' `--'   `'-'   '.(_,_).'
```

# Running PITest Mutations Report

`./gradlew pitest`

Mutation Report gets written to `/build/reports/pitest/<timestamp>/index.html`.


# Running Jacoco Coverage Report

`./gradlew test jacocoTestReport`

Coverage Report gets written to `/build/reports/jacoco/html/index.html`.

# Generating Color Ascii Art

https://spring-boot-banner-gen.cfapps.io/banner

# Generating Text Ascii Art

http://patorjk.com/software/taag/#p=display&f=Flower%20Power&t=Anushka

# 4 Types of Data Access Being Used
## Take a look at each, and decide what you like best (JdbcTemplates are best for database access in PCF, IMO).
- Hibernate named functions using keywords to magically query without JPQL;
- Hibernate functions annotated with @Query to pass a JPQL string;
- JPA with Criteria and Predicates; and
- JdbcTemplates
