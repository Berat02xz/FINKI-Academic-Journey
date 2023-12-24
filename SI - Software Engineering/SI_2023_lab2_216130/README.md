<b>Berat Ahmetaj 216130</b>

--------------------------------

![CFG drawio](https://github.com/BeratAhmetaj/SI_2023_lab2_216130/assets/56788474/cbc88b30-bc41-4dfb-ba88-472fa7d64033)

--------------------------------

3. цикломатската комплексност е 9, стигнато е со броење на региони од горенаведеното CFG

--------------------------------

4. Одлука: if (user==null || user.getPassword()==null || user.getEmail()==null)
Прва гранка: user==null
Тест случај 1: user = null
Втора гранка: user.getPassword()==null
Тест случај 2: user = new User("username", null, "email")
Трета гранка: user.getEmail()==null
Тест случај 3: user = new User("username", "password", null)
Четврта гранка: сите услови се исполнети
Тест случај 4: user = new User("username", "password", "email")
Одлука: if (user.getUsername()==null)

Прва гранка: user.getUsername()==null
Тест случај 5: user = new User(null, "password", "email")
Втора гранка: сите услови се исполнети
Тест случај 6: user = new User("username", "password", "email")
Одлука: if (user.getEmail().contains("@") && user.getEmail().contains("."))

Прва гранка: user.getEmail().contains("@") && user.getEmail().contains(".")
Тест случај 7: user = new User("username", "password", "email@domain.com")
Втора гранка: сите услови се исполнети
Тест случај 8: user = new User("username", "password", "email@domain.com") (повторно, за проверка на други делови од кодот)
Одлука: if (existingUser.getEmail() == user.getEmail())

Прва гранка: existingUser.getEmail() == user.getEmail()
Тест случај 9: existingUser = new User("existingUser", "password", "email@domain.com"), user = new User("username", "password", "email@domain.com")
Втора гранка: сите услови се исполнети
Тест случај 10: existingUser = new User("existingUser", "password", "existing@domain.com"), user = new User("username", "password", "email@domain.com") (повторно, за проверка на други делови од кодот)

--------------------------------

5. user==null, user.getPassword()==null, user.getEmail()==null
Тест случај 1: user = null
Објаснување: Сите услови се исполнети, бидејќи user е null, иако user.getPassword() и user.getEmail() не се евалуираат (short-circuit evaluation).
user==null, user.getPassword()==null, user.getEmail()!=null

Тест случај 2: user = null, user.getEmail() = "email"
Објаснување: Првиот услов user==null е исполнет, па не се евалуираат останатите услови.
user==null, user.getPassword()!=null, user.getEmail()==null

Тест случај 3: user = null, user.getPassword() = "password"
Објаснување: Првиот услов user==null е исполнет, па не се евалуираат останатите услови.
user==null, user.getPassword()!=null, user.getEmail()!=null

Тест случај 4: user = null, user.getPassword() = "password", user.getEmail() = "email"
Објаснување: Првиот услов user==null е исполнет, па не се евалуираат останатите услови.
user!=null, user.getPassword()==null, user.getEmail()==null

Тест случај 5: user = new User("username", null, null)
Објаснување: Првиот услов user==null не е исполнет, па се евалуираат останатите услови.
user!=null, user.getPassword()==null, user.getEmail()!=null

Тест случај 6: user = new User("username", null, "email")
Објаснување: Првиот услов user==null не е исполнет, па се евалуираат останатите услови.
user!=null, user.getPassword()!=null, user.getEmail()==null

Тест случај 7: user = new User("username", "password", null)
Објаснување: Први
