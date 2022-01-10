package bada_proj2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalesDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SalesDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Sale> list() {
        String sql = "SELECT * FROM SALES";

        List<Sale> listSale = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Sale.class));

        return listSale;
    }

    public void save(Sale sale) {
        SimpleJdbcInsert insertAction = new SimpleJdbcInsert(jdbcTemplate);
        insertAction.withTableName("sales").usingColumns("item", "quantity", "amount");

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);
        insertAction.execute(param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM SALES WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void update(Sale sale) {
        String sql = "UPDATE SALES SET item=:item, quantity=:quantity, amount=:amount WHERE id=:id";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sale);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }

    public Sale get(int id) {
        Object[] args = {id};
        String sql = "SELECT * FROM SALES WHERE id = " + args[0];
        Sale sale = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Sale.class));
        return sale;
    }
}
