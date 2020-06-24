package com.meriga.mfinance.repository;

import com.meriga.mfinance.dto.AverageExpensesDto;
import com.meriga.mfinance.dto.ExpenseStatisticsDto;
import com.meriga.mfinance.dto.PlanningPercentagesDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceStatisticsRepositoryImpl implements FinanceStatisticsRepositoryCustom{

    @PersistenceContext
    EntityManager em;


    @Override
    public List<ExpenseStatisticsDto> getExpenseStatistics(Date date) {
        String sql =
            "select pl.id, pl.category_id as category_id, " +
                "ct.name as category_name, " +
                "pl.date as date, " +
                "pl.value as planned_value, " +
                "(" +
                "   select sum(value) from cash_flow cf where cf.category_id = pl.category_id" +
                "    and cf.date between first_day(pl.date) and last_day(pl.date)" +
                "" +
                ") as amount_spent, " +
                "(" +
                "   select pl.value - sum(value) from cash_flow cf where cf.category_id = pl.category_id " +
                "    and cf.date between first_day(pl.date) and last_day(pl.date)" +
                ") as remaining_value " +
                "from planning pl inner join category ct on pl.category_id = ct.id " +
                "where pl.date between first_day('" + date.toString() +"') and last_day('" + date.toString() +"');";

        Query q = em.createNativeQuery(sql, Tuple.class);

        List<Tuple> tuples = q.getResultList();
        List<ExpenseStatisticsDto> result = tuples.stream().map(
            t -> new ExpenseStatisticsDto(
                ((BigInteger) t.get("id")).longValue(),
                ((BigInteger) t.get("category_id")).longValue(),
                t.get("category_name").toString(),
                (Date)t.get("date"),
                (BigDecimal)t.get("planned_value"),
                (BigDecimal)t.get("amount_spent"),
                (BigDecimal)t.get("remaining_value")
            )
        ).collect(Collectors.toList());


        return result;
    }

    @Override
    public List<AverageExpensesDto> getAverageExpensesOverPlanningsOnLastMonths(int numberOfMonths) {
        String sql = "select pl.category_id as id, ct.name, avg(pl.value) as average from planning pl " +
            "inner join category ct on pl.category_id = ct.id " +
            " where date > DATE_SUB(last_day(now()), INTERVAL "+ numberOfMonths + " MONTH) group by pl.category_id;";

        Query q = em.createNativeQuery(sql, Tuple.class);

        List<Tuple> tuples = q.getResultList();
        List<AverageExpensesDto> result = tuples.stream().map(
            t -> new AverageExpensesDto(
                ((BigInteger) t.get("id")).longValue(),
                (t.get("name")).toString(),
                (BigDecimal)t.get("average"),
                numberOfMonths
            )
        ).collect(Collectors.toList());

        return result;
    }

    @Override
    public List<PlanningPercentagesDto> getPlanningSpentPercentagesForCurrentMonth() {
        String sql = "select pl.id, " +
            "ct.name as category_name, " +
            "pl.value as planned_value, " +
            "( " +
            "  select (sum(value)/pl.value)*100 from cash_flow cf where cf.category_id = pl.category_id " +
            "    and cf.date between first_day(pl.date) and last_day(pl.date) " +
            "  " +
            ") as percentage " +
            "from planning pl inner join category ct on pl.category_id = ct.id " +
            "where pl.date between first_day(now()) and last_day(now());";

        Query q = em.createNativeQuery(sql, Tuple.class);

        List<Tuple> tuples = q.getResultList();
        List<PlanningPercentagesDto> result = tuples.stream().map(
            t -> new PlanningPercentagesDto(
                ((BigInteger) t.get("id")).longValue(),
                (t.get("category_name")).toString(),
                (BigDecimal)t.get("planned_value"),
                (BigDecimal)t.get("percentage")
            )
        ).collect(Collectors.toList());

        return result;
    }
}
