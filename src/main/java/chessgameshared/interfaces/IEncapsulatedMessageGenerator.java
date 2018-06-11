package chessgameshared.interfaces;

import chessgameshared.messages.EncapsulatedMessage;

public interface IEncapsulatedMessageGenerator {
    EncapsulatedMessage generateEncapsulatedMessage(Object content);

    String generateEncapsulatedMessageString(Object content);
}
