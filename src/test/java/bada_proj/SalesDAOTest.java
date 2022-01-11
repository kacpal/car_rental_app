package bada_proj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalesDAOTest {

    private SalesDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:ORCL1");
        dataSource.setUsername("KACPER");
        dataSource.setPassword("orcl");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        dao = new SalesDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void list() {
        List<Sale> listSale = dao.list();

        assertTrue(listSale.isEmpty());
    }

    @Test
    void save() {
        Sale sale = new Sale(0, "Test item", 1, 1234);
        dao.save(sale);
    }

    @Test
    void delete() {
        int id = 1;
        dao.delete(1);
    }

    @Test
    void update() {
        Sale sale = new Sale();
        sale.setId(1);
        sale.setItem("Harnas");
        sale.setQuantity(10);
        sale.setAmount(2137);

        dao.update(sale);
    }

    @Test
    void get() {
        int id = 1;
        Sale sale = dao.get(id);

        assertNotNull(sale);
    }
}