package glory_schema;

/**
 *
 * @author TeamStark
 */

public class VariableElement extends GloryElement {

    private String _invitationMsg;
    private int _userId;

    public VariableElement() {
        //in case
    }

    public VariableElement(String InvitationMsg) {
        //this.InvitationMsg=InvitationMsg
        this._invitationMsg = InvitationMsg;
    }

    public String getInvitationMsg() {
        return this._invitationMsg;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    public int get_userId() {
        return this._userId;
    }
}
