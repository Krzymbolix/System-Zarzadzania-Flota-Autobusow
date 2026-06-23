package com.example.bada;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController implements WebMvcConfigurer {
    private final BazyDAO bazyDao;
    private final PracownicyDAO pracownicyDao;
    private final PrzystankiDAO przystankiDao;
    private final KursyDAO kursyDao;
    private final Linia_PrzystanekDAO liniaPrzystanekDao;
    private final AdministratorzyDAO administratorzyDao;
    private final LinieDAO linieDao;
    private final AutobusyDAO autobusyDao;
    private final KierowcyDAO kierowcyDao;

    public AppController(BazyDAO bazyDao, PracownicyDAO pracownicyDao,
                         PrzystankiDAO przystankiDao, KursyDAO kursyDao,
                         Linia_PrzystanekDAO liniaPrzystanekDao,
                         AdministratorzyDAO administratorzyDao,
                         LinieDAO linieDao, AutobusyDAO autobusyDao,
                         KierowcyDAO kierowcyDao) {
        this.bazyDao = bazyDao;
        this.pracownicyDao = pracownicyDao;
        this.przystankiDao = przystankiDao;
        this.kursyDao = kursyDao;
        this.liniaPrzystanekDao = liniaPrzystanekDao;
        this.administratorzyDao = administratorzyDao;
        this.linieDao = linieDao;
        this.autobusyDao = autobusyDao;
        this.kierowcyDao = kierowcyDao;
    }

    // ========== STRONY PUBLICZNE ==========

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return "index";
    }

    @GetMapping("/index")
    public String viewIndexPage(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/search_by_stop")
    public String viewSearchByStopPage(Model model) {
        List<Przystanki> listPrzystanki = przystankiDao.list();
        model.addAttribute("listPrzystanki", listPrzystanki);
        return "search_by_stop";
    }

    @GetMapping("/search_by_line")
    public String viewSearchByLinePage(Model model) {
        List<Linie> listLinie = linieDao.list();
        model.addAttribute("listLinie", listLinie);
        return "search_by_line";
    }

    @GetMapping("/found_stop")
    public String viewSearchByStop(@RequestParam(value = "id", required = true) int stop, Model model) {
        Przystanki przystanek = przystankiDao.findByID(stop);
        List<Linia_Przystanek> polaczenia = liniaPrzystanekDao.findByStop(stop);
        model.addAttribute("przystanek", przystanek);
        model.addAttribute("polaczenia", polaczenia);
        return "found_stop";
    }

    @GetMapping("/found_line")
    public String viewSearchByLine(@RequestParam(value = "line", required = true) String line, Model model) {
        List<Linia_Przystanek> polaczenia = liniaPrzystanekDao.findByLine(line);
        Linie linia = linieDao.findByNr(line).get(0);
        model.addAttribute("linia", linia);
        model.addAttribute("polaczenia", polaczenia);
        return "found_line";
    }

    // ========== PANEL ADMINISTRATORA - DASHBOARD ==========

    @GetMapping("/admin/main")
    public String viewAdminDashboard(Model model) {
        // Pobierz dane dla dashboardu
        List<Bazy> listBazy = bazyDao.list();
        List<Pracownicy> listPracownicy = pracownicyDao.list();
        List<Przystanki> listPrzystanki = przystankiDao.list();
        List<Linie> listLinie = linieDao.list();
        List<Kursy> listKursy = kursyDao.list();
        List<Linia_Przystanek> listLiniaPrzystanek = liniaPrzystanekDao.list();
        List<Administratorzy> listAdministratorzy = administratorzyDao.list();
        List<Autobusy> listAutobusy = autobusyDao.list();
        List<Kierowcy> listKierowcy = kierowcyDao.list();

        model.addAttribute("listBazy", listBazy);
        model.addAttribute("listPracownicy", listPracownicy);
        model.addAttribute("listPrzystanki", listPrzystanki);
        model.addAttribute("listLinie", listLinie);
        model.addAttribute("listKursy", listKursy);
        model.addAttribute("listLiniaPrzystanek", listLiniaPrzystanek);
        model.addAttribute("listAdministratorzy", listAdministratorzy);
        model.addAttribute("listAutobusy", listAutobusy);
        model.addAttribute("listKierowcy", listKierowcy);

        return "admin/admin_main";
    }

    // ========== BAZY ==========

    @GetMapping("/admin/bazy")
    public String viewBazyPage(Model model,
                               @RequestParam(value = "success", required = false) String success,
                               @RequestParam(value = "error", required = false) String error) {

        List<Bazy> listBazy = bazyDao.list();
        model.addAttribute("listBazy", listBazy);

        if (!model.containsAttribute("bazy")) {
            model.addAttribute("bazy", new Bazy());
        }

        return "admin/bazy";
    }

//    @GetMapping("/admin/saveBaza")
//    public String saveBaza(@ModelAttribute("bazy") Bazy bazy,
//                           Model model,
//                           RedirectAttributes redirectAttributes) {  // ← Dodaj to
//
//        try {
//            // Sprawdź czy numer bazy już istnieje
//            if (bazyDao.exists(bazy.getNr_bazy())) {
//                redirectAttributes.addFlashAttribute("error",
//                        "Baza o numerze " + bazy.getNr_bazy() + " już istnieje!");
//                redirectAttributes.addFlashAttribute("bazy", bazy); // Zachowaj wpisane dane
//                return "redirect:/admin/bazy";  // ← REDIRECT zamiast return view
//            }
//
//            // Walidacja kodu pocztowego
//            if (!bazy.getKod_pocztowy().matches("\\d{2}-\\d{3}")) {
//                redirectAttributes.addFlashAttribute("error",
//                        "Nieprawidłowy format kodu pocztowego. Wymagany format: 00-000");
//                redirectAttributes.addFlashAttribute("bazy", bazy);
//                return "redirect:/admin/bazy";  // ← REDIRECT
//            }
//
//            bazyDao.save(bazy);
//            return "redirect:/admin/bazy";
//
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error",
//                    "Wystąpił błąd podczas dodawania bazy: " + e.getMessage());
//            redirectAttributes.addFlashAttribute("bazy", bazy);
//            return "redirect:/admin/bazy";  // ← REDIRECT
//        }
//    }
//
//    @GetMapping("/admin/updateBaza")
//    public String updateBaza(@ModelAttribute("bazy") Bazy bazy,
//                             Model model,
//                             RedirectAttributes redirectAttributes) {
//
//        try {
//            // Walidacja kodu pocztowego
//            if (!bazy.getKod_pocztowy().matches("\\d{2}-\\d{3}")) {
//                redirectAttributes.addFlashAttribute("error",
//                        "Nieprawidłowy format kodu pocztowego. Wymagany format: 00-000");
//                return "redirect:/admin/bazy";  // ← REDIRECT
//            }
//
//            bazyDao.update(bazy);
//            return "redirect:/admin/bazy";
//
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error",
//                    "Wystąpił błąd podczas aktualizacji bazy: " + e.getMessage());
//            return "redirect:/admin/bazy";  // ← REDIRECT
//        }
//    }
//
//    @GetMapping("/admin/deleteBaza/{id}")
//    public String deleteBaza(@PathVariable int id, Model model) {
//        try {
//            // Sprawdź czy baza jest używana
//            if (bazyDao.isUsed(id)) {
//                return "redirect:/admin/bazy";
//            }
//
//            bazyDao.delete(id);
//            return "redirect:/admin/bazy";
//        } catch (Exception e) {
//            return "redirect:/admin/bazy";
//        }
//    }
//
    // API do sprawdzania dostępności numeru bazy (AJAX)
    @GetMapping("/admin/checkBaza/{nr_bazy}")
    @ResponseBody
    public Map<String, Object> checkBaza(@PathVariable int nr_bazy) {
        boolean exists = bazyDao.exists(nr_bazy);
        return Map.of(
                "exists", exists,
                "nr_bazy", nr_bazy,
                "message", exists ?
                        "Numer bazy " + nr_bazy + " jest już zajęty!" :
                        "Numer bazy " + nr_bazy + " jest dostępny."
        );
    }

    // ========== BAZY AJAX ENDPOINTS ==========

    @PostMapping("/admin/saveBazaAjax")
    @ResponseBody
    public Map<String, Object> saveBazaAjax(@RequestBody Bazy bazy) {

        System.out.println("DEBUG: saveBazaAjax called");
        System.out.println("DEBUG: nr_bazy = " + bazy.getNr_bazy());
        System.out.println("DEBUG: nazwa_ulicy = " + bazy.getNazwa_ulicy());
        System.out.println("DEBUG: kod_pocztowy = " + bazy.getKod_pocztowy());
        Map<String, Object> response = new HashMap<>();

        try {
            // Walidacja: sprawdź czy numer bazy już istnieje
            if (bazyDao.exists(bazy.getNr_bazy())) {
                response.put("success", false);
                response.put("type", "error");
                response.put("message", "Baza o numerze " + bazy.getNr_bazy() + " już istnieje!");
                return response;
            }

            // Walidacja formatu kodu pocztowego
            if (!bazy.getKod_pocztowy().matches("\\d{2}-\\d{3}")) {
                response.put("success", false);
                response.put("type", "error");
                response.put("message", "Nieprawidłowy format kodu pocztowego. Wymagany format: 00-000");
                return response;
            }

            // Zapisz do bazy danych
            bazyDao.save(bazy);

            response.put("success", true);
            response.put("type", "success");
            response.put("message", "Baza została dodana pomyślnie!");
            response.put("baza", bazy); // Zwracamy dodaną bazę

        } catch (Exception e) {
            response.put("success", false);
            response.put("type", "error");
            response.put("message", "Wystąpił błąd: " + e.getMessage());
        }

        return response;
    }

    @PostMapping("/admin/updateBazaAjax")
    @ResponseBody
    public Map<String, Object> updateBazaAjax(@RequestBody Bazy bazy) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Walidacja formatu kodu pocztowego
            if (!bazy.getKod_pocztowy().matches("\\d{2}-\\d{3}")) {
                response.put("success", false);
                response.put("type", "error");
                response.put("message", "Nieprawidłowy format kodu pocztowego. Wymagany format: 00-000");
                return response;
            }

            // Sprawdź czy baza istnieje przed aktualizacją
            Bazy existingBaza = bazyDao.get(bazy.getNr_bazy());
            if (existingBaza == null) {
                response.put("success", false);
                response.put("type", "error");
                response.put("message", "Baza o numerze " + bazy.getNr_bazy() + " nie istnieje!");
                return response;
            }

            // Aktualizuj w bazie danych
            bazyDao.update(bazy);

            response.put("success", true);
            response.put("type", "success");
            response.put("message", "Baza została zaktualizowana!");

        } catch (Exception e) {
            response.put("success", false);
            response.put("type", "error");
            response.put("message", "Wystąpił błąd: " + e.getMessage());
        }

        return response;
    }

    @DeleteMapping("/admin/deleteBazaAjax/{id}")
    @ResponseBody
    public Map<String, Object> deleteBazaAjax(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Sprawdź czy baza istnieje
            Bazy baza = bazyDao.get(id);
            if (baza == null) {
                response.put("success", false);
                response.put("type", "error");
                response.put("message", "Baza o numerze " + id + " nie istnieje!");
                return response;
            }

            // Sprawdź czy baza jest używana
            if (bazyDao.isUsed(id)) {
                response.put("success", false);
                response.put("type", "warning");
                response.put("message", "Nie można usunąć bazy, ponieważ jest używana przez pracowników, autobusy lub linie!");
                return response;
            }

            // Usuń z bazy danych
            bazyDao.delete(id);

            response.put("success", true);
            response.put("type", "success");
            response.put("message", "Baza została usunięta!");

        } catch (Exception e) {
            response.put("success", false);
            response.put("type", "error");
            response.put("message", "Wystąpił błąd: " + e.getMessage());
        }

        return response;
    }

    // ========== PRACOWNICY ==========

    @GetMapping("/admin/pracownicy")
    public String viewPracownicyPage(Model model) {
        List<Pracownicy> listPracownicy = pracownicyDao.list();
        List<Bazy> listBazy = bazyDao.list();
        model.addAttribute("listPracownicy", listPracownicy);
        model.addAttribute("listBazy", listBazy);
        model.addAttribute("pracownik", new Pracownicy());
        return "admin/pracownicy";
    }

    @PostMapping("/admin/savePracownik")
    public String savePracownik(@ModelAttribute("pracownik") Pracownicy pracownik) {
        pracownicyDao.save(pracownik);
        return "redirect:/admin/pracownicy";
    }

    @PostMapping("/admin/updatePracownik")
    public String updatePracownik(@ModelAttribute("pracownik") Pracownicy pracownik) {
        pracownicyDao.update(pracownik);
        return "redirect:/admin/pracownicy";
    }

    @GetMapping("/admin/deletePracownik/{id}")
    public String deletePracownik(@PathVariable int id) {
        pracownicyDao.delete(id);
        return "redirect:/admin/pracownicy";
    }

    // ========== KIEROWCY ==========

    @GetMapping("/admin/kierowcy")
    public String viewKierowcyPage(Model model) {
        List<Kierowcy> listKierowcy = kierowcyDao.list();
        List<Pracownicy> listPracownicy = pracownicyDao.list();
        model.addAttribute("listKierowcy", listKierowcy);
        model.addAttribute("listPracownicy", listPracownicy);
        model.addAttribute("kierowca", new Kierowcy());
        return "admin/kierowcy";
    }

    @PostMapping("/admin/saveKierowca")
    public String saveKierowca(@ModelAttribute("kierowca") Kierowcy kierowca) {
        kierowcyDao.save(kierowca);
        return "redirect:/admin/kierowcy";
    }

    @PostMapping("/admin/updateKierowca")
    public String updateKierowca(@ModelAttribute("kierowca") Kierowcy kierowca) {
        kierowcyDao.update(kierowca);
        return "redirect:/admin/kierowcy";
    }

    @GetMapping("/admin/deleteKierowca/{id}")
    public String deleteKierowca(@PathVariable int id) {
        kierowcyDao.delete(id);
        return "redirect:/admin/kierowcy";
    }

    // ========== AUTOBUSY ==========

    @GetMapping("/admin/autobusy")
    public String viewAutobusyPage(Model model) {
        List<Autobusy> listAutobusy = autobusyDao.list();
        List<Bazy> listBazy = bazyDao.list();
        model.addAttribute("listAutobusy", listAutobusy);
        model.addAttribute("listBazy", listBazy);
        model.addAttribute("autobus", new Autobusy());
        return "admin/autobusy";
    }

    @PostMapping("/admin/saveAutobus")
    public String saveAutobus(@ModelAttribute("autobus") Autobusy autobus) {
        autobusyDao.save(autobus);
        return "redirect:/admin/autobusy";
    }

    @PostMapping("/admin/updateAutobus")
    public String updateAutobus(@ModelAttribute("autobus") Autobusy autobus) {
        autobusyDao.update(autobus);
        return "redirect:/admin/autobusy";
    }

    @GetMapping("/admin/deleteAutobus/{id}")
    public String deleteAutobus(@PathVariable int id) {
        autobusyDao.delete(id);
        return "redirect:/admin/autobusy";
    }

    // ========== KURSY ==========

    @GetMapping("/admin/kursy")
    public String viewKursyPage(Model model) {
        List<Kursy> listKursy = kursyDao.list();
        List<Kierowcy> listKierowcy = kierowcyDao.list();
        List<Linie> listLinie = linieDao.list();
        List<Autobusy> listAutobusy = autobusyDao.list();

        model.addAttribute("listKursy", listKursy);
        model.addAttribute("listKierowcy", listKierowcy);
        model.addAttribute("listLinie", listLinie);
        model.addAttribute("listAutobusy", listAutobusy);
        model.addAttribute("kurs", new Kursy());
        return "admin/kursy";
    }

    @PostMapping("/admin/saveKurs")
    public String saveKurs(@ModelAttribute("kurs") Kursy kurs) {
        kursyDao.save(kurs);
        return "redirect:/admin/kursy";
    }

    @PostMapping("/admin/updateKurs")
    public String updateKurs(@ModelAttribute("kurs") Kursy kurs) {
        kursyDao.update(kurs);
        return "redirect:/admin/kursy";
    }

    @GetMapping("/admin/deleteKurs/{id}")
    public String deleteKurs(@PathVariable int id) {
        kursyDao.delete(id);
        return "redirect:/admin/kursy";
    }

    // ========== LINIA_PRZYSTANEK ==========

    @GetMapping("/admin/linia-przystanek")
    public String viewLiniaPrzystanekPage(Model model) {
        List<Linia_Przystanek> listLiniaPrzystanek = liniaPrzystanekDao.list();
        List<Linie> listLinie = linieDao.list();
        List<Przystanki> listPrzystanki = przystankiDao.list();

        model.addAttribute("listLiniaPrzystanek", listLiniaPrzystanek);
        model.addAttribute("listLinie", listLinie);
        model.addAttribute("listPrzystanki", listPrzystanki);
        model.addAttribute("liniaPrzystanek", new Linia_Przystanek());
        return "admin/linia_przystanek";
    }

    @PostMapping("/admin/saveLiniaPrzystanek")
    public String saveLiniaPrzystanek(@ModelAttribute("liniaPrzystanek") Linia_Przystanek liniaPrzystanek) {
        liniaPrzystanekDao.save(liniaPrzystanek);
        return "redirect:/admin/linia-przystanek";
    }

    @PostMapping("/admin/updateLiniaPrzystanek")
    public String updateLiniaPrzystanek(@ModelAttribute("liniaPrzystanek") Linia_Przystanek liniaPrzystanek) {
        liniaPrzystanekDao.update(liniaPrzystanek);
        return "redirect:/admin/linia-przystanek";
    }

    @GetMapping("/admin/deleteLiniaPrzystanek/{id}")
    public String deleteLiniaPrzystanek(@PathVariable int id) {
        liniaPrzystanekDao.delete(id);
        return "redirect:/admin/linia-przystanek";
    }

    // ========== PRZYSTANKI ==========

    @GetMapping("/admin/przystanki")
    public String viewPrzystankiPage(Model model) {
        List<Przystanki> listPrzystanki = przystankiDao.list();
        model.addAttribute("listPrzystanki", listPrzystanki);
        model.addAttribute("przystanek", new Przystanki());
        return "admin/przystanki";
    }

    @PostMapping("/admin/savePrzystanek")
    public String savePrzystanek(@ModelAttribute("przystanek") Przystanki przystanek) {
        przystankiDao.save(przystanek);
        return "redirect:/admin/przystanki";
    }

    @PostMapping("/admin/updatePrzystanek")
    public String updatePrzystanek(@ModelAttribute("przystanek") Przystanki przystanek) {
        przystankiDao.update(przystanek);
        return "redirect:/admin/przystanki";
    }

    @GetMapping("/admin/deletePrzystanek/{id}")
    public String deletePrzystanek(@PathVariable int id) {
        przystankiDao.delete(id);
        return "redirect:/admin/przystanki";
    }

    // ========== ADMINISTRATORZY ==========

    @GetMapping("/admin/administratorzy")
    public String viewAdministratorzyPage(Model model) {
        List<Administratorzy> listAdministratorzy = administratorzyDao.list();
        List<Pracownicy> listPracownicy = pracownicyDao.list();
        model.addAttribute("listAdministratorzy", listAdministratorzy);
        model.addAttribute("listPracownicy", listPracownicy);
        model.addAttribute("administrator", new Administratorzy());
        return "admin/administratorzy";
    }

    @PostMapping("/admin/saveAdministrator")
    public String saveAdministrator(@ModelAttribute("administrator") Administratorzy administrator) {
        administratorzyDao.save(administrator);
        return "redirect:/admin/administratorzy";
    }

    @PostMapping("/admin/updateAdministrator")
    public String updateAdministrator(@ModelAttribute("administrator") Administratorzy administrator) {
        administratorzyDao.update(administrator);
        return "redirect:/admin/administratorzy";
    }

    @GetMapping("/admin/deleteAdministrator/{id}")
    public String deleteAdministrator(@PathVariable int id) {
        administratorzyDao.delete(id);
        return "redirect:/admin/administratorzy";
    }

    // ========== LINIE ==========

    @GetMapping("/admin/linie")
    public String viewLiniePage(Model model) {
        List<Linie> listLinie = linieDao.list();
        List<Bazy> listBazy = bazyDao.list();
        model.addAttribute("listLinie", listLinie);
        model.addAttribute("listBazy", listBazy);
        model.addAttribute("linia", new Linie());
        return "admin/linie";
    }

    @PostMapping("/admin/saveLinia")
    public String saveLinia(@ModelAttribute("linia") Linie linia) {
        linieDao.save(linia);
        return "redirect:/admin/linie";
    }

    @PostMapping("/admin/updateLinia")
    public String updateLinia(@ModelAttribute("linia") Linie linia) {
        linieDao.update(linia);
        return "redirect:/admin/linie";
    }

    @GetMapping("/admin/deleteLinia/{id}")
    public String deleteLinia(@PathVariable String id) {
        linieDao.delete(id);
        return "redirect:/admin/linie";
    }

    // ========== PROFIL UŻYTKOWNIKA ==========

    @GetMapping("/admin/profil")
    public String viewProfilPage(Model model, Principal principal,
                                 @RequestParam(value = "error", required = false) String error,
                                 @RequestParam(value = "success", required = false) String success) {

        String email = principal.getName();
        Pracownicy pracownik = pracownicyDao.findByEmail(email);
        List<Bazy> listBazy = bazyDao.list();

        model.addAttribute("pracownik", pracownik);
        model.addAttribute("listBazy", listBazy);

        if (error != null) {
            model.addAttribute("error", error);
        }
        if (success != null) {
            model.addAttribute("success", success);
        }

        return "admin/profil";
    }

    @PostMapping("/admin/updateProfil")
    public String updateProfil(@ModelAttribute("pracownik") Pracownicy pracownik,
                               Principal principal,
                               @RequestParam(value = "nowe_haslo", required = false) String noweHaslo,
                               Model model) {

        String email = principal.getName();
        Pracownicy obecnyPracownik = pracownicyDao.findByEmail(email);

        if (obecnyPracownik.getNr_pracownika() != pracownik.getNr_pracownika()) {
            return "redirect:/admin/profil?error=Brak uprawnień do edycji tego profilu!";
        }

        if (noweHaslo != null && !noweHaslo.trim().isEmpty()) {
            pracownik.setHaslo(noweHaslo);
        } else {
            pracownik.setHaslo(obecnyPracownik.getHaslo());
        }

        pracownik.setAktywny(obecnyPracownik.getAktywny());

        pracownicyDao.update(pracownik);
        return "redirect:/admin/profil?success=Dane zostały zaktualizowane pomyślnie!";
    }

    @PostMapping("/admin/zmienHaslo")
    public String zmienHaslo(@RequestParam("stare_haslo") String stareHaslo,
                             @RequestParam("nowe_haslo") String noweHaslo,
                             @RequestParam("powtorz_haslo") String powtorzHaslo,
                             Principal principal) {

        String email = principal.getName();
        Pracownicy pracownik = pracownicyDao.findByEmail(email);

        if (!pracownik.getHaslo().equals(stareHaslo)) {
            return "redirect:/admin/profil?error=Błędne obecne hasło!";
        }

        if (!noweHaslo.equals(powtorzHaslo)) {
            return "redirect:/admin/profil?error=Nowe hasła nie są identyczne!";
        }

        pracownik.setHaslo(noweHaslo);
        pracownicyDao.update(pracownik);

        return "redirect:/admin/profil?success=Hasło zostało zmienione pomyślnie!";
    }

    // ========== PANEL UŻYTKOWNIKA ==========

    @GetMapping("/user/main")
    public String viewUserPage(Model model) {
        List<Pracownicy> listPracownicy = pracownicyDao.list();
        model.addAttribute("listPracownicy", listPracownicy);
        return "user/user_main";
    }

    // ========== PRZEKIEROWANIA DLA STARYCH ENDPOINTÓW ==========

    @GetMapping("/admin_main")
    public String oldAdminPageRedirect() {
        return "redirect:/admin/main";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String oldSaveBazaRedirect(@ModelAttribute("bazy") Bazy bazy) {
        return "redirect:/admin/saveBaza";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String oldUpdateBazaRedirect(@ModelAttribute("bazy") Bazy bazy) {
        return "redirect:/admin/updateBaza";
    }

    @GetMapping("/delete/{id}")
    public String oldDeleteBazaRedirect(@PathVariable int id) {
        return "redirect:/admin/deleteBaza/" + id;
    }

    // ========== AUTENTYKACJA I PRZEKIEROWANIA ==========

    @Controller
    public static class DashboardController {
        @GetMapping("/perspectives")
        public String defaultSuccessUrl(HttpServletRequest request) {
            if (request.isUserInRole("ADMIN")) {
                return "redirect:/admin/main";
            } else if (request.isUserInRole("USER")) {
                return "redirect:/user/main";
            } else {
                return "redirect:/index";
            }
        }
    }

    // ========== API DLA AUTOCOMPLETE ==========

    @RestController
    public class PrzystankiApiController {
        private final PrzystankiDAO przystankiDao;

        public PrzystankiApiController(PrzystankiDAO przystankiDao) {
            this.przystankiDao = przystankiDao;
        }

        @GetMapping("/api/stops")
        public List<Map<String, String>> autocomplete(@RequestParam String q) {
            return przystankiDao.findByName(q)
                    .stream()
                    .map(p -> Map.of(
                            "id", String.valueOf(p.getNr_przystanku()),
                            "name", p.getNazwa() + " " + p.getNr_porzadkowy())
                    )
                    .toList();
        }
    }

    // ========== VIEW CONTROLLER REGISTRY ==========

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Publiczne strony
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/search_by_stop").setViewName("search_by_stop");
        registry.addViewController("/search_by_line").setViewName("search_by_line");
        registry.addViewController("/perspectives").setViewName("perspectives");

        // Panel admina - dashboard
        registry.addViewController("/admin/main").setViewName("admin/admin_main");

        // Panel admina - zarządzanie tabelami
        registry.addViewController("/admin/bazy").setViewName("admin/bazy");
        registry.addViewController("/admin/pracownicy").setViewName("admin/pracownicy");
        registry.addViewController("/admin/kierowcy").setViewName("admin/kierowcy");
        registry.addViewController("/admin/autobusy").setViewName("admin/autobusy");
        registry.addViewController("/admin/kursy").setViewName("admin/kursy");
        registry.addViewController("/admin/linia-przystanek").setViewName("admin/linia_przystanek");
        registry.addViewController("/admin/przystanki").setViewName("admin/przystanki");
        registry.addViewController("/admin/administratorzy").setViewName("admin/administratorzy");
        registry.addViewController("/admin/linie").setViewName("admin/linie");
        registry.addViewController("/admin/profil").setViewName("admin/profil");

        // Panel użytkownika
        registry.addViewController("/user/main").setViewName("user/user_main");

        // Przekierowania dla starych endpointów
        registry.addViewController("/admin_main").setViewName("redirect:/admin/main");
    }
}