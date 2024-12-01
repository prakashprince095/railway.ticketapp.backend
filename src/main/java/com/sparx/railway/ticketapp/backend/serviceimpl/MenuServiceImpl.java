package com.sparx.railway.ticketapp.backend.serviceimpl;

import com.sparx.railway.ticketapp.backend.dto.GetTicketByPnrNoDTO;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.entities.PassangerDetailEntity;
import com.sparx.railway.ticketapp.backend.entities.TicketEntity;
import com.sparx.railway.ticketapp.backend.repository.PassangerDetailEntityRepository;
import com.sparx.railway.ticketapp.backend.repository.TicketEntityRepository;
import com.sparx.railway.ticketapp.backend.service.MenuService;
import com.sparx.railway.ticketapp.backend.util.TimeandDateUtil;
import com.sparx.railway.ticketapp.backend.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl  implements MenuService {
    private final TokenUtil tokenUtil;
    private final TicketEntityRepository ticketEntityRepository;
    private final PassangerDetailEntityRepository passangerDetailEntityRepository;
    private final TimeandDateUtil timeandDateUtil;
    @Override
    public ServiceResponseDTO getMyUpcomimgBooking(HttpServletRequest request) throws ParseException {
        try{
            String accountEmailId=tokenUtil.getUserNameFromTheToken(request.getHeader("Authorization"));
            List<GetTicketByPnrNoDTO> response=new ArrayList<GetTicketByPnrNoDTO>();
            LocalDate date=LocalDate.now();
            Date todaysDate=timeandDateUtil.setTravelDate(String.valueOf(date));
            List<TicketEntity> ticketList =ticketEntityRepository.findUpcomingBookedTicket(accountEmailId, false,todaysDate);
            if(ticketList.size() <= 0){
                return ServiceResponseDTO.builder().response("No Upcoming Bookings found").timestamp(null).status(HttpStatus.NO_CONTENT.value()).code(HttpStatus.OK.value()).build();
            }
            for(TicketEntity ticket : ticketList ){
                String pnrNo=ticket.getPnrNo();
                List<GetTicketByPnrNoDTO.PassangerDetails> passangerDetailsList=new ArrayList<GetTicketByPnrNoDTO.PassangerDetails>();
                List<PassangerDetailEntity> passangerDetailEntityList=passangerDetailEntityRepository.fetchThePassangerListByPnr(pnrNo);
                for(PassangerDetailEntity passangerDetailEntity : passangerDetailEntityList){
                    GetTicketByPnrNoDTO.PassangerDetails passangerDetails =GetTicketByPnrNoDTO.PassangerDetails.builder().passangerName(passangerDetailEntity.getPassangerName())
                            .passagerAge(passangerDetailEntity.getAge())
                            .gender(passangerDetailEntity.getGender())
                            .seatNo(passangerDetailEntity.getSeatNo())
                            .coachNo(passangerDetailEntity.getCoachNo()).build();
                    passangerDetailsList.add(passangerDetails);
                }
                GetTicketByPnrNoDTO getTicketByPnrNoDTO =GetTicketByPnrNoDTO.builder()
                        .pnrNo(ticket.getPnrNo())
                        .journeyStartDate(String.valueOf(ticket.getStartDateOfJourney()))
                        .journeyEndDate(String.valueOf(ticket.getEndDateOfJourney()))
                        .trainName(null)
                        .trainNo(ticket.getTrainNo())
                        .price(String.valueOf(ticket.getTicketPrice()))
                        .ticketBookedDate(String.valueOf(ticket.getCreatedAt()))
                        .status((ticket.isCancelled()) ? "Cancelled" : "BOOKED")
                        .ticketClass(String.valueOf(ticket.getClass()))
                        .passangerDetailsList(passangerDetailsList).build();
                response.add(getTicketByPnrNoDTO);


            }
            return ServiceResponseDTO.builder().response(response).timestamp(null).status(HttpStatus.OK.value()).code(HttpStatus.OK.value()).build();
        }catch(Exception ex){
            return new ServiceResponseDTO(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(), "Failed to get upcoming bookings"+ex.getMessage());
        }
    }
}
