package com.example.reportservice.command.rest;

import com.example.reportservice.command.CreateReportCommand;
import com.example.reportservice.command.UpdateReportCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/report")
public class ReportCommandController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final CommandGateway commandGateway;

    @Autowired
    public ReportCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createReport(@RequestBody CreateReportModel model){
        CreateReportCommand command = CreateReportCommand.builder()
                ._id(UUID.randomUUID().toString())
                .userReportId(model.getUserReportId())
                .userReportName(model.getUserReportName())
                .typeReport(model.getTypeReport())
                .itemIdReport(model.getItemIdReport())
                .reportHeader(model.getReportHeader())
                .reportDescription(model.getReportDescription())
                .banDescription(model.getBanDescription())
                .judge(model.isJudge()).build();
        String result;
        try {
            result = commandGateway.sendAndWait(command);

        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @PutMapping(value = "/update")
    public String updateReport(@RequestBody UpdateReportModel model){
        UpdateReportCommand command = UpdateReportCommand.builder()
                ._id(model.get_id())
                .userReportId(model.getUserReportId())
                .userReportName(model.getUserReportName())
                .typeReport(model.getTypeReport())
                .itemIdReport(model.getItemIdReport())
                .reportHeader(model.getReportHeader())
                .reportDescription(model.getReportDescription())
                .banDescription(model.getBanDescription())
                .judge(model.isJudge()).build();
        String result;
        try{
            String idUpdate = commandGateway.sendAndWait(command);
            result = "Update Complete ID: " + idUpdate;
        } catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
    @PutMapping(value = "/banreview")
    public String banReview(@RequestBody UpdateReportModel model){
        UpdateReportCommand command = UpdateReportCommand.builder()
                ._id(model.get_id())
                .userReportId(model.getUserReportId())
                .userReportName(model.getUserReportName())
                .typeReport(model.getTypeReport())
                .itemIdReport(model.getItemIdReport())
                .reportHeader(model.getReportHeader())
                .reportDescription(model.getReportDescription())
                .banDescription(model.getBanDescription())
                .judge(true).build();
        String result;
        try{
            String idUpdate = commandGateway.sendAndWait(command);
            rabbitTemplate.convertAndSend("ReviewExchange", "banreview", model.getItemIdReport());
            result = "Ban Review ID: " + model.getItemIdReport();
        } catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @PutMapping(value = "/notbanreview")
    public String notBanReview(@RequestBody UpdateReportModel model){
        UpdateReportCommand command = UpdateReportCommand.builder()
                ._id(model.get_id())
                .userReportId(model.getUserReportId())
                .userReportName(model.getUserReportName())
                .typeReport(model.getTypeReport())
                .itemIdReport(model.getItemIdReport())
                .reportHeader(model.getReportHeader())
                .reportDescription(model.getReportDescription())
                .banDescription(model.getBanDescription())
                .judge(true).build();
        String result;
        try{
            String idUpdate = commandGateway.sendAndWait(command);
            result = "Not Ban Review ID: " + model.getItemIdReport();
        } catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @PutMapping(value = "/bancomment")
    public String banComment(@RequestBody UpdateReportModel model){
        UpdateReportCommand command = UpdateReportCommand.builder()
                ._id(model.get_id())
                .userReportId(model.getUserReportId())
                .userReportName(model.getUserReportName())
                .typeReport(model.getTypeReport())
                .itemIdReport(model.getItemIdReport())
                .reportHeader(model.getReportHeader())
                .reportDescription(model.getReportDescription())
                .banDescription(model.getBanDescription())
                .judge(true).build();
        String result;
        try{
            String idUpdate = commandGateway.sendAndWait(command);
            rabbitTemplate.convertAndSend("CommentExchange", "bancomment", model.getItemIdReport());
            result = "Ban Comment ID: " + model.getItemIdReport();
        } catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @PutMapping(value = "/notbancomment")
    public String notBanComment(@RequestBody UpdateReportModel model){
        UpdateReportCommand command = UpdateReportCommand.builder()
                ._id(model.get_id())
                .userReportId(model.getUserReportId())
                .userReportName(model.getUserReportName())
                .typeReport(model.getTypeReport())
                .itemIdReport(model.getItemIdReport())
                .reportHeader(model.getReportHeader())
                .reportDescription(model.getReportDescription())
                .banDescription(model.getBanDescription())
                .judge(true).build();
        String result;
        try{
            String idUpdate = commandGateway.sendAndWait(command);
            result = "Not Ban Comment ID: " + model.getItemIdReport();
        } catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
    @PutMapping(value = "/duplicate")
    public String judgeDuplicate(@RequestBody UpdateReportModel model){
        UpdateReportCommand command = UpdateReportCommand.builder()
                ._id(model.get_id())
                .userReportId(model.getUserReportId())
                .userReportName(model.getUserReportName())
                .typeReport(model.getTypeReport())
                .itemIdReport(model.getItemIdReport())
                .reportHeader(model.getReportHeader())
                .reportDescription(model.getReportDescription())
                .banDescription("Already Judge This Item")
                .judge(true).build();
        String result;
        try{
            String idUpdate = commandGateway.sendAndWait(command);
            result = "Update Judge";
        } catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
