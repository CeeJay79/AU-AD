//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.10.09 at 04:08:45 PM EEST 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="npc_clients">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="npc_client" type="{}Npc" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "npcClients"
})
@XmlRootElement(name = "Collection")
public class Collection {

    @XmlElement(name = "npc_clients", required = true)
    protected Collection.NpcClients npcClients;

    /**
     * Gets the value of the npcClients property.
     * 
     * @return
     *     possible object is
     *     {@link Collection.NpcClients }
     *     
     */
    public Collection.NpcClients getNpcClients() {
        return npcClients;
    }

    /**
     * Sets the value of the npcClients property.
     * 
     * @param value
     *     allowed object is
     *     {@link Collection.NpcClients }
     *     
     */
    public void setNpcClients(Collection.NpcClients value) {
        this.npcClients = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="npc_client" type="{}Npc" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "npcClient"
    })
    public static class NpcClients {

        @XmlElement(name = "npc_client", required = true)
        protected List<Npc> npcClient;

        /**
         * Gets the value of the npcClient property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the npcClient property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNpcClient().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Npc }
         * 
         * 
         */
        public List<Npc> getNpcClient() {
            if (npcClient == null) {
                npcClient = new ArrayList<Npc>();
            }
            return this.npcClient;
        }

    }

}
