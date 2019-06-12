package com.xwc1125.chain5j.console;

import java.io.File;
import java.io.IOException;

import com.xwc1125.chain5j.crypto.CipherException;
import com.xwc1125.chain5j.crypto.Credentials;
import com.xwc1125.chain5j.crypto.WalletUtils;
import com.xwc1125.chain5j.utils.Files;

import static com.xwc1125.chain5j.codegen.Console.exitError;
import static com.xwc1125.chain5j.crypto.Keys.PRIVATE_KEY_LENGTH_IN_HEX;

/**
 * Create Ethereum wallet file from a provided private key.
 */
public class KeyImporter extends WalletManager {

    public KeyImporter() {
    }

    public KeyImporter(IODevice console) {
        super(console);
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            new KeyImporter().run(null, args[0]);
        } else {
            new KeyImporter().run();
        }
    }

    static void main(IODevice console) {
        new KeyImporter(console).run();
    }

    private void run(String icapPrefix, String input) {
        File keyFile = new File(input);

        if (keyFile.isFile()) {
            String privateKey = null;
            try {
                privateKey = Files.readString(keyFile);
            } catch (IOException e) {
                exitError("Unable to read file " + input);
            }

            createWalletFile(icapPrefix, privateKey.trim());
        } else {
            createWalletFile(icapPrefix, input.trim());
        }
    }

    private void run() {
        String icapPrefix = console.readLine(
                "Please enter the icap prefix, and it can empty: ").trim();
        String input = console.readLine(
                "Please enter the hex encoded private key or key file location: ").trim();

        run(icapPrefix, input);
    }

    private void createWalletFile(String icapPrefix, String privateKey) {
        if (!WalletUtils.isValidPrivateKey(privateKey)) {
            exitError("Invalid private key specified, must be "
                    + PRIVATE_KEY_LENGTH_IN_HEX
                    + " digit hex value");
        }

        Credentials credentials = Credentials.create(icapPrefix, privateKey);
        String password = getPassword("Please enter a wallet file password: ");

        String destinationDir = getDestinationDir();
        File destination = createDir(destinationDir);

        try {
            String walletFileName = WalletUtils.generateWalletFile(icapPrefix,
                    password, credentials.getEcKeyPair(), destination, true);
            console.printf("Wallet file " + walletFileName
                    + " successfully created in: " + destinationDir + "\n");
        } catch (CipherException | IOException e) {
            exitError(e);
        }
    }
}
