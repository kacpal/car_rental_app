package bada_proj;

import bada_proj.entities.Marki;
import bada_proj.entities.Pojazdy;
import bada_proj.entities.Wypozyczalnie;
import bada_proj.repositories.MarkiRepository;
import bada_proj.repositories.ModeleRepository;
import bada_proj.repositories.PojazdyRepository;
import bada_proj.repositories.WypozyczalnieRepository;
import oracle.sql.DATE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.xml.namespace.QName;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
    }

    @Autowired
    private SalesDAO dao;

    @Autowired
    private PojazdyRepository pojazdyRepository;

    @Autowired
    private MarkiRepository markiRepository;

    @Autowired
    private ModeleRepository modeleRepository;

    @Autowired
    private WypozyczalnieRepository wypozyczalnieRepository;


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
        if (rodzaj_paliwa != null) {
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
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_form");
        Sale sale = dao.get(id);
        mav.addObject("sale", sale);

        return mav;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("sale") Sale sale) {
        dao.update(sale);

        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") long id) {
        pojazdyRepository.deleteById(id);

        return "redirect:/";
    }
}
