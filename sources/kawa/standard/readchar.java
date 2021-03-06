package kawa.standard;

import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure0or1;
import gnu.mapping.WrongType;
import gnu.text.Char;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class readchar extends Procedure0or1 {
    public static final readchar peekChar = new readchar(true);
    public static final readchar readChar = new readchar(false);
    boolean peeking;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public readchar(boolean peeking2) {
        super(peeking2 ? "peek-char" : "read-char");
        this.peeking = peeking2;
    }

    /* access modifiers changed from: package-private */
    public final Object readChar(InPort port) {
        try {
            int ch = this.peeking ? port.peek() : port.read();
            if (ch < 0) {
                return Sequence.eofValue;
            }
            return Char.make(ch);
        } catch (IOException e) {
            throw new RuntimeException("IO Exception caught");
        }
    }

    /* access modifiers changed from: package-private */
    public final Object readChar(Reader port) {
        int ch;
        try {
            if (this.peeking) {
                port.mark(1);
                ch = port.read();
                port.reset();
            } else {
                ch = port.read();
            }
            if (ch < 0) {
                return Sequence.eofValue;
            }
            return Char.make(ch);
        } catch (IOException e) {
            throw new RuntimeException("IO Exception caught");
        }
    }

    /* access modifiers changed from: package-private */
    public final Object readChar(InputStream port) {
        int ch;
        try {
            if (this.peeking) {
                port.mark(1);
                ch = port.read();
                port.reset();
            } else {
                ch = port.read();
            }
            if (ch < 0) {
                return Sequence.eofValue;
            }
            return Char.make(ch);
        } catch (IOException e) {
            throw new RuntimeException("IO Exception caught");
        }
    }

    public final Object apply0() {
        return readChar(InPort.inDefault());
    }

    public final Object apply1(Object arg1) {
        if (arg1 instanceof InPort) {
            return readChar((InPort) arg1);
        }
        if (arg1 instanceof Reader) {
            return readChar((Reader) arg1);
        }
        if (arg1 instanceof InputStream) {
            return readChar((InputStream) arg1);
        }
        throw new WrongType((Procedure) this, 1, arg1, "<input-port>");
    }
}
