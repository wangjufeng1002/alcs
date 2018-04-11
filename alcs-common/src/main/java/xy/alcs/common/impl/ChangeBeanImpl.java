package xy.alcs.common.impl;

import org.springframework.stereotype.Component;
import xy.alcs.domain.Contest;
import xy.alcs.dto.ContestDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 1:20 2017-12-07
 */
@Component
public class ChangeBeanImpl implements ChangeBean {

    @Override
    public List<ContestDto> changeBean(List<Contest> contests) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ContestDto dto;
        String time;
        Calendar calendar= Calendar.getInstance();
        Calendar tempCalendar = Calendar.getInstance();
        for(Contest contest : contests){
            dto = new ContestDto();
            dto.setCid(contest.getCid());
            dto.setSummary(contest.getSummary());
            dto.setTitle(contest.getTitle());

            time =format.format(contest.getEnrollStartDate()) + " "+"--"+" "+format.format(contest.getEnrollEndDate());
            dto.setEnrollTime(time);
            time =format.format(contest.getStartDate()) + " "+"--"+" "+format.format(contest.getEndDate());
            dto.setStartEndTime(time);

            tempCalendar.setTime(contest.getEnrollEndDate());
            dto.setnEnroll( calendar.before(tempCalendar)  ? "1":"0");

        }

        return null;
    }
}
