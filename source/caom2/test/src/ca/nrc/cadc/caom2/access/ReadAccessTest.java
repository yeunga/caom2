/*
************************************************************************
*******************  CANADIAN ASTRONOMY DATA CENTRE  *******************
**************  CENTRE CANADIEN DE DONNÉES ASTRONOMIQUES  **************
*
*  (c) 2011.                            (c) 2011.
*  Government of Canada                 Gouvernement du Canada
*  National Research Council            Conseil national de recherches
*  Ottawa, Canada, K1A 0R6              Ottawa, Canada, K1A 0R6
*  All rights reserved                  Tous droits réservés
*
*  NRC disclaims any warranties,        Le CNRC dénie toute garantie
*  expressed, implied, or               énoncée, implicite ou légale,
*  statutory, of any kind with          de quelque nature que ce
*  respect to the software,             soit, concernant le logiciel,
*  including without limitation         y compris sans restriction
*  any warranty of merchantability      toute garantie de valeur
*  or fitness for a particular          marchande ou de pertinence
*  purpose. NRC shall not be            pour un usage particulier.
*  liable in any event for any          Le CNRC ne pourra en aucun cas
*  damages, whether direct or           être tenu responsable de tout
*  indirect, special or general,        dommage, direct ou indirect,
*  consequential or incidental,         particulier ou général,
*  arising from the use of the          accessoire ou fortuit, résultant
*  software.  Neither the name          de l'utilisation du logiciel. Ni
*  of the National Research             le nom du Conseil National de
*  Council of Canada nor the            Recherches du Canada ni les noms
*  names of its contributors may        de ses  participants ne peuvent
*  be used to endorse or promote        être utilisés pour approuver ou
*  products derived from this           promouvoir les produits dérivés
*  software without specific prior      de ce logiciel sans autorisation
*  written permission.                  préalable et particulière
*                                       par écrit.
*
*  This file is part of the             Ce fichier fait partie du projet
*  OpenCADC project.                    OpenCADC.
*
*  OpenCADC is free software:           OpenCADC est un logiciel libre ;
*  you can redistribute it and/or       vous pouvez le redistribuer ou le
*  modify it under the terms of         modifier suivant les termes de
*  the GNU Affero General Public        la “GNU Affero General Public
*  License as published by the          License” telle que publiée
*  Free Software Foundation,            par la Free Software Foundation
*  either version 3 of the              : soit la version 3 de cette
*  License, or (at your option)         licence, soit (à votre gré)
*  any later version.                   toute version ultérieure.
*
*  OpenCADC is distributed in the       OpenCADC est distribué
*  hope that it will be useful,         dans l’espoir qu’il vous
*  but WITHOUT ANY WARRANTY;            sera utile, mais SANS AUCUNE
*  without even the implied             GARANTIE : sans même la garantie
*  warranty of MERCHANTABILITY          implicite de COMMERCIALISABILITÉ
*  or FITNESS FOR A PARTICULAR          ni d’ADÉQUATION À UN OBJECTIF
*  PURPOSE.  See the GNU Affero         PARTICULIER. Consultez la Licence
*  General Public License for           Générale Publique GNU Affero
*  more details.                        pour plus de détails.
*
*  You should have received             Vous devriez avoir reçu une
*  a copy of the GNU Affero             copie de la Licence Générale
*  General Public License along         Publique GNU Affero avec
*  with OpenCADC.  If not, see          OpenCADC ; si ce n’est
*  <http://www.gnu.org/licenses/>.      pas le cas, consultez :
*                                       <http://www.gnu.org/licenses/>.
*
*  $Revision: 5 $
*
************************************************************************
*/

package ca.nrc.cadc.caom2.access;

import ca.nrc.cadc.util.Log4jInit;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author pdowler
 */
public class ReadAccessTest 
{
    private static final Logger log = Logger.getLogger(ReadAccessTest.class);

    static
    {
        Log4jInit.setLevel("ca.nrc.cadc.caom2", Level.INFO);
    }

    Long assetID = new Long(666L);
    Long groupID = new Long(1000L);

    //@Test
    public void testTemplate()
    {
        try
        {

        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }

    @Test
    public void testObservationMetaReadAccess()
    {
        try
        {
            ReadAccess ra = new ObservationMetaReadAccess(assetID, groupID);
            ra.toString();
            Assert.assertEquals(assetID, ra.getAssetID());
            Assert.assertEquals(groupID, ra.getGroupID());
            
            Assert.assertEquals(ra, ra);
            Assert.assertEquals(0, ra.compareTo(ra));
            
            ReadAccess raeq = new ObservationMetaReadAccess(assetID, groupID);
            Assert.assertEquals(ra, raeq);
            Assert.assertEquals(0, ra.compareTo(raeq));
            
            ReadAccess rane = new ObservationMetaReadAccess(assetID, groupID+1L);
            Assert.assertFalse(ra.equals(rane));
            Assert.assertEquals(-1, ra.compareTo(rane));
            
            rane = new ObservationMetaReadAccess(assetID+1L, groupID);
            Assert.assertFalse(ra.equals(rane));
            Assert.assertEquals(-1, ra.compareTo(rane));
            
            rane = new PlaneMetaReadAccess(assetID, groupID); // diff class
            Assert.assertFalse(ra.equals(rane));
            Assert.assertEquals(-1, ra.compareTo(rane)); // obs < plane
            
            rane = null;
            Assert.assertFalse(ra.equals(rane));
            
            
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }

    @Test
    public void testPlaneMetaReadAccess()
    {
        try
        {
            ReadAccess ra = new PlaneMetaReadAccess(assetID, groupID);
            ra.toString();
            Assert.assertEquals(assetID, ra.getAssetID());
            Assert.assertEquals(groupID, ra.getGroupID());
            
            Assert.assertEquals(ra, ra);
            Assert.assertEquals(0, ra.compareTo(ra));
            
            ReadAccess raeq = new PlaneMetaReadAccess(assetID, groupID);
            Assert.assertEquals(ra, raeq);
            Assert.assertEquals(0, ra.compareTo(raeq));
            
            ReadAccess rane = new PlaneMetaReadAccess(assetID, groupID+1L);
            Assert.assertFalse(ra.equals(rane));
            Assert.assertEquals(-1, ra.compareTo(rane));
            
            rane = new PlaneMetaReadAccess(assetID+1L, groupID);
            Assert.assertFalse(ra.equals(rane));
            Assert.assertEquals(-1, ra.compareTo(rane));
            
            rane = new ObservationMetaReadAccess(assetID, groupID); // diff class
            Assert.assertFalse(ra.equals(rane));
            Assert.assertEquals(1, ra.compareTo(rane)); // plane > obs
            
            rane = null;
            Assert.assertFalse(ra.equals(rane));
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }

    @Test
    public void testPlaneDataReadAccess()
    {
        try
        {
            ReadAccess ra = new PlaneDataReadAccess(assetID, groupID);
            ra.toString();
            Assert.assertEquals(assetID, ra.getAssetID());
            Assert.assertEquals(groupID, ra.getGroupID());
            
            Assert.assertEquals(ra, ra);
            Assert.assertEquals(0, ra.compareTo(ra));
            
            ReadAccess raeq = new PlaneDataReadAccess(assetID, groupID);
            Assert.assertEquals(ra, raeq);
            Assert.assertEquals(0, ra.compareTo(raeq));
            
            ReadAccess rane = new PlaneDataReadAccess(assetID, groupID+1L);
            Assert.assertFalse(ra.equals(rane));
            Assert.assertEquals(-1, ra.compareTo(rane));
            
            rane = new PlaneDataReadAccess(assetID+1L, groupID);
            Assert.assertFalse(ra.equals(rane));
            Assert.assertEquals(-1, ra.compareTo(rane));
            
            rane = new ObservationMetaReadAccess(assetID, groupID); // diff class
            Assert.assertFalse(ra.equals(rane));
            Assert.assertEquals(1, ra.compareTo(rane)); // plane > obs
            
            rane = null;
            Assert.assertFalse(ra.equals(rane));
        }
        catch(Exception unexpected)
        {
            log.error("unexpected exception", unexpected);
            Assert.fail("unexpected exception: " + unexpected);
        }
    }
}