package chessgameshared.interfaces;

import chessgameshared.messages.EncapsulatingMessage;

public interface IEncapsulatedMessageGenerator {
    EncapsulatingMessage generateEncapsulatedMessage(Object content);

    String generateEncapsulatedMessageString(Object content);
}
