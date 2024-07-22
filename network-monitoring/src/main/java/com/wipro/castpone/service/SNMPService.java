package com.wipro.castpone.service;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.stereotype.Service;

@Service
public class SNMPService {

    public String getSNMPData(String address, String community) {
        try {
            Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
            snmp.listen();
            Target target = new CommunityTarget();
            target.setAddress(GenericAddress.parse(address));
            ((CommunityTarget) target).setCommunity(new OctetString(community));
            target.setRetries(2);
            target.setTimeout(1500);
            target.setVersion(SnmpConstants.version2c);

            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.1.0")));
            pdu.setType(PDU.GET);

            ResponseEvent response = snmp.send(pdu, target);
            snmp.close();

            if (response != null && response.getResponse() != null) {
                return response.getResponse().get(0).getVariable().toString();
            } else {
                return "No response from SNMP agent";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }
}
