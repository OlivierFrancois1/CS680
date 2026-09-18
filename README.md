# CS680 (Object-Oriented Design) – Setup & Progress Log

Everything done so far, in order, so the setup can be rebuilt from scratch.

---

## 1. Course essentials (from Note 02)

- Homework is built with **Ant**. You turn in `*.java` files + a `build.xml`.
- The professor runs your build script **from a shell, not an IDE**, using the latest stable Ant.
- **If the build fails, the homework is not graded.** Test with `ant` in a terminal before every submission.
- Use a real IDE (VS Code, IntelliJ, Eclipse), not a memopad-style editor.
- Learn both: Ant inside the IDE **and** Ant on the command line.
- Use `java.time` (never `java.util.Date` or `Calendar`).

---

## 2. Environment: GitHub Codespaces

I used a Codespace because it has a real terminal (needed for Ant) and I was not on my own laptop.

1. Create a GitHub repo named `CS680`.
2. On the repo page: **Code → Codespaces → Create codespace on main**.
3. Workspace folder is `/workspaces/CS680`.

### Check Java

```bash
java -version
javac -version
```

### Install Ant (it is NOT preinstalled)

```bash
sudo apt-get update && sudo apt-get install -y ant
ant -version
```

Expected: `Apache Ant(TM) version 1.10.x`.

> On my own computer: install a JDK (17 or 21), then Ant, then confirm all three
> commands above work (`java -version`, `javac -version`, `ant -version`).

---

## 3. Project layout

```
CS680/
├── build.xml        <- in the ROOT (not inside src/)
├── .gitignore       <- contains "bin/"
├── README.md
└── src/
    └── Main.java
```

`bin/` is created by Ant and is NOT committed.

Commands to create the layout:

```bash
mkdir src
mv Main.java src/
rm Main.class          # remove stray compiled file from the root
echo "bin/" >> .gitignore
```

---

## 4. build.xml

Compiles everything from scratch (`clean` runs first) into `bin/`.

```xml
<project name="CS680" default="compile" basedir=".">
    <property name="src.dir" value="src"/>
    <property name="bin.dir" value="bin"/>

    <target name="clean">
        <delete dir="${bin.dir}"/>
    </target>

    <target name="compile" depends="clean">
        <mkdir dir="${bin.dir}"/>
        <javac srcdir="${src.dir}" destdir="${bin.dir}" includeantruntime="false"/>
    </target>
</project>
```

Quick way to create it from the terminal: use a `cat > build.xml` heredoc, paste the XML, then end it.

---

## 5. Build and run

From `/workspaces/CS680`:

```bash
ant                       # should end with BUILD SUCCESSFUL
java -cp bin Main         # runs the compiled program
```

Result I got: `BUILD SUCCESSFUL` and `Hello, Olivier!`.

---

## 6. Save to GitHub

```bash
git add .
git commit -m "Set up Ant build"
git push
```

---

## 7. Mistakes I hit (and fixes)

| Problem | Cause | Fix |
|---|---|---|
| `Could not find or load main class main.java` / `file not found: main.java` | Java and Linux are **case-sensitive**; file is `Main.java` | Use exact case: `javac Main.java`, `java Main` |
| `ant: command not found` | Ant is not preinstalled in Codespaces | `sudo apt-get update && sudo apt-get install -y ant` |
| Empty `build.xml` inside `src/` | Created in the wrong folder by mistake | `rm src/build.xml`; keep `build.xml` in the root |
| `java -cp bin Main` printed before `Buildfile:` | Both commands were pasted together | Harmless; run them one at a time |

Rule of thumb: `javac` takes a **file name** (`Main.java`), `java` takes a **class name** (`Main`, no extension).

---

## 8. Step 3 – Java refresher (in progress)

### Part 1: static vs. instance members

`src/Main.java`:

```java
class Address {
    private static int MAX_LENGTH = 15;   // one copy, shared by all instances
    private String street;                // one copy per instance

    Address(String street) {
        this.street = street;
    }

    String getStreet() { return this.street; }

    static int getMaxLength() { return MAX_LENGTH; }   // static: no access to 'street'
}

public class Main {
    public static void main(String[] args) {
        Address a1 = new Address("abc");
        Address a2 = new Address("xyz");

        System.out.println(a1.getStreet());          // abc
        System.out.println(a2.getStreet());          // xyz
        System.out.println(Address.getMaxLength());  // 15
    }
}
```

Expected output: `abc`, `xyz`, `15`.

Break-it exercises:
1. In `getMaxLength()`, try `return street.length();`. Compile error, because a static method cannot access non-static fields.
2. Make `MAX_LENGTH` non-static and call `Address.getMaxLength()`. Read the error and explain why.

### Still to do (from the prep plan)

- Part 2: `final`, constructors, `super(...)`
- Generics, `ArrayList<T>`, `Iterator` (and why a raw `ArrayList` gives a compile error on `getTuition()`)
- UML: draw class diagrams (`Customer`/`Address`), translate to Java and back
- Encapsulation: `Person` with private `ssn`, no setter
- Inheritance and polymorphism: `Student`, `Account`, `Polygon`
- `java.time`: `Instant`, `Duration`, `LocalDate`, `Period`, `DateTimeFormatter`

---

## 9. Quick-start checklist for a fresh machine

1. Install JDK, Ant, Git (or open a Codespace and install Ant).
2. Clone the repo: `git clone <repo-url>` and `cd CS680`.
3. Confirm `java -version`, `javac -version`, `ant -version`.
4. Run `ant`, then `java -cp bin Main`.
5. Before every homework submission: run `ant` from a **terminal** and confirm `BUILD SUCCESSFUL`.
