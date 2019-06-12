package com.xwc1125.chain5j.console;

import java.io.File;
import java.io.IOException;

import com.xwc1125.chain5j.crypto.CipherException;
import com.xwc1125.chain5j.crypto.Credentials;
import com.xwc1125.chain5j.crypto.WalletUtils;

import static com.xwc1125.chain5j.codegen.Console.exitError;

/**
 * Simple class for creating a wallet file.
 */
public class WalletUpdater extends WalletManager {

    public WalletUpdater() {
    }

    public WalletUpdater(IODevice console) {
        super(console);
    }

    public static void main(String[] args) {
        if (args.length != 1 && args.length != 2) {
            exitError("You must provide an existing wallet file");
        } else {
            if (args.length == 1) {
                new WalletUpdater().run(null, args[0]);
            } else {
                new WalletUpdater().run(args[1], args[0]);
            }
        }
    }

    public static void main(IODevice console, String icapPrefix, String walletFileLocation) {
        new WalletUpdater(console).run(icapPrefix, walletFileLocation);
    }

    private void run(String icapPrefix, String walletFileLocation) {
        File walletFile = new File(walletFileLocation);
        Credentials credentials = getCredentials(icapPrefix, walletFile);

        console.printf("Wallet for address " + credentials.getAddress() + " loaded\n");

        String newPassword = getPassword("Please enter a new wallet file password: ");

        String destinationDir = getDestinationDir();
        File destination = createDir(destinationDir);

        try {
            String walletFileName = WalletUtils.generateWalletFile(icapPrefix,
                    newPassword, credentials.getEcKeyPair(), destination, true);
            console.printf("New wallet file " + walletFileName
                    + " successfully created in: " + destinationDir + "\n");
        } catch (CipherException | IOException e) {
            exitError(e);
        }

        String delete = console.readLine(
                "Would you like to delete your existing wallet file (Y/N)? [N]: ");
        if (delete.toUpperCase().equals("Y")) {
            if (!walletFile.delete()) {
                exitError("Unable to remove wallet file\n");
            } else {
                console.printf("Deleted previous wallet file: %s\n", walletFile.getName());
            }
        }
    }
}
