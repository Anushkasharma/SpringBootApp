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
