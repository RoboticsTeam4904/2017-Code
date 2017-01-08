# navXLibrary
Team 4904's navX submodule.

## Purpose
This submodule is meant to be used within another repository to enable the parent repository to use the navX library.

## Use instructions
This submodule should be placed in a parent repo's `lib` folder.

Once it's there, add two lines of code to the parent repo.

In `.classpath`, add

```
<classpathentry kind="lib" path="lib/navXLibrary/navx_frc.jar" sourcepath="lib/navXLibrary/navx_frc-sources.jar"/>
```

In `build.properties`, if you have no `userLibs` line, add

```
userLibs=lib/navXLibrary/navx_frc.jar
```

If you do have a `userLibs` line, add `;lib/navXLibrary/navx_frc.jar` onto the end (note the semicolon).

## Update instructions
This submodule should be updated whenever Kauai Labs, Inc. releases a new compiled `navx_frc.jar` file. When that happens:

1. Overwrite this repo's `navx_frc.jar` with the updated version.
2. Run `sh generate-sources.sh` to automatically regenerate the `navx_frc-sources.jar` file.
3. Branch, commit, test, pull request, get it merged
4. Update the submodule references in parent repos by running the command `git submodule add https://github.com/RoboticsTeam4904/navXLibrary.git lib/navXLibrary` from the parent repo root
