package br.inatel.dm112.service;

import br.inatel.dm112.adapter.MailAdapter;
import br.inatel.dm112.model.MailRequestData;
import br.inatel.dm112.model.MailStatusResponse;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    public MailStatusResponse sendMail(MailRequestData mailData) {
        if (mailData.getFrom() == null ||
                mailData.getPassword() == null ||
                mailData.getTo() == null) {
            if (mailData.getContent() == null && mailData.getMessage() == null)
                return new MailStatusResponse(MailStatusResponse.STATUS.ERROR.ordinal(),
                        mailData.getFrom(), mailData.getTo(), "Null values not allowed in MailRequestData.");
        }

        MailAdapter sender = new MailAdapter();
        try {
            if (mailData.getContent() == null)
                sender.sendMail(mailData.getFrom(), mailData.getPassword(), mailData.getTo(), mailData.getMessage());
            else
                sender.sendMail(mailData.getFrom(), mailData.getPassword(), mailData.getTo(), mailData.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            return new MailStatusResponse(MailStatusResponse.STATUS.ERROR.ordinal(),
                    mailData.getFrom(), mailData.getTo(), e.getMessage());
        }
        return new MailStatusResponse(MailStatusResponse.STATUS.OK.ordinal(),
                mailData.getFrom(), mailData.getTo(), "Email sent success.");
    }

}