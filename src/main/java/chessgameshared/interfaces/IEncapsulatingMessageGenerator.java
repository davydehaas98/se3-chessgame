package chessgameshared.interfaces;

import chessgameshared.messages.EncapsulatingMessage;

public interface IEncapsulatingMessageGenerator {
    EncapsulatingMessage generateMessage(Object content);
    String generateMessageString(Object content);
}
