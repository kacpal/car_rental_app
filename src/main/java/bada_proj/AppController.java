package bada_proj;

import bada_proj.entities.Adresy;
import bada_proj.entities.Klienci;
import bada_proj.entities.Pojazdy;
import bada_proj.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/client").setViewName("client");

    }

    @Autowired
    private PojazdyRepository pojazdyRepository;

    @Autowired
    private MarkiRepository markiRepository;

    @Autowired
    private ModeleRepository modeleRepository;

    @Autowired
    private WypozyczalnieRepository wypozyczalnieRepository;

    @Autowired
    private KlienciRepository klienciRepository;

    @Autowired
    private AdresyRepository adresyRepository;

    @Autowired
    private PocztyRepository pocztyRepository;


    @RequestMapping("/")
    public String viewHomePage(Model model, Long rok_produkcji, Long nr_modelu,
                                Long ilosc_miejsc, String rodzaj_paliwa) {
        model.addAttribute("listModel", modeleRepository.findAll());
        model.addAttribute("listMarka", markiRepository.findAll());

        List<Pojazdy> pojazdyList = (List<Pojazdy>) pojazdyRepository.findAll();
        if (rok_produkcji != null) {
            List<Pojazdy> list = pojazdyRepository.findPojazdyByRokProdukcji(rok_produkcji);
            pojazdyList.retainAll(list);
        }
        if (nr_modelu != null) {
            List<Pojazdy> list = pojazdyRepository.findPojazdyByNrModelu(
                    modeleRepository.findById(nr_modelu).orElse(null));
            pojazdyList.retainAll(list);
        }
        if (ilosc_miejsc != null) {
            List<Pojazdy> list = pojazdyRepository.findPojazdyByIloscMiejsc(ilosc_miejsc);
            pojazdyList.retainAll(list);
        }
        if (rodzaj_paliwa != "") {
            List<Pojazdy> list = pojazdyRepository.findPojazdyByRodzajPaliwa(rodzaj_paliwa);
            pojazdyList.retainAll(list);
        }

        model.addAttribute("listCar", pojazdyList);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewForm(Model model) {
        Pojazdy pojazd = new Pojazdy();
        model.addAttribute("pojazd", pojazd);
        model.addAttribute("listModele", modeleRepository.findAll());
        model.addAttribute("listMarki", markiRepository.findAll());
        model.addAttribute("wypozyczalnia", wypozyczalnieRepository.findAll());
        return "new_form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("pojazd") Pojazdy pojazd) {
        pojazdyRepository.save(pojazd);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_form");
        Optional<Pojazdy> pojazd = pojazdyRepository.findById(id);
        mav.addObject("pojazd", pojazd);

        return mav;
    }

    @RequestMapping("/editClient/{id}")
    public ModelAndView showEditClientForm(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("edit_form_client");
        Optional<Klienci> klient = klienciRepository.findById(id);
        mav.addObject("klient", klient);

        return mav;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("pojazd") Pojazdy pojazd) {
        pojazdyRepository.update(pojazd.getId(), pojazd.getRokProdukcji(), pojazd.getRodzajPaliwa(), pojazd.getIloscMiejsc());

        return "redirect:/";
    }

    @RequestMapping(value = "/updateClient", method = RequestMethod.POST)
    public String update(@ModelAttribute("klient") Klienci klient) {
        klienciRepository.update(klient.getId(), klient.getImie(), klient.getNazwisko());

        return "redirect:/client";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") long id) {
        pojazdyRepository.deleteById(id);

        return "redirect:/";
    }

    @RequestMapping("/register_form")
    public String showRegisterForm(Model model) {
        Klienci klient = new Klienci();
        model.addAttribute("klient", klient);

        Adresy adres = new Adresy();
        model.addAttribute("adres", adres);

        model.addAttribute("listAdres", adresyRepository.findAll());
        model.addAttribute("listPoczta", pocztyRepository.findAll());

        return "register_form";
    }

    @RequestMapping(value = "/process_register")
    public String processRegister(@ModelAttribute("klient") Klienci klient, @ModelAttribute("adres") Adresy adres) {
        adres.setNrPoczty(pocztyRepository.findFirstByIdIsNot((long) -1));

        NoOpPasswordEncoder passwordEncoder = (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
        String encodedPassword = passwordEncoder.encode(klient.getHaslo());
        klient.setHaslo(encodedPassword);
        adresyRepository.save(adres);
        klient.setNrAdresu(adres);
        klienciRepository.save(klient);

        return "redirect:/";
    }

    @RequestMapping("/klienci")
    public String listUsers(Model model) {
        model.addAttribute("listUsers", klienciRepository.findAll());

        return "users";
    }

    @RequestMapping("/client")
    public String getUserId(Principal principal, Model model) {
        Klienci klient = klienciRepository.findKlienciByNazwaUzytkownika(principal.getName());
        model.addAttribute("klienciList", klient);


        return "client";
    }

}
